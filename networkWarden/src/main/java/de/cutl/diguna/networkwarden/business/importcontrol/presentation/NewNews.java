package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.Configuration;
import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadNews;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chris
 */
@Model
public class NewNews {
    
    private static final DateTimeFormatter TODAY = DateTimeFormatter.ofPattern("yyyy_MM_dd");
    private static final DateTimeFormatter NOW = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private UploadNews upload;
    
    private List<String> languages;
    
    private UploadedFile file;
    
    @Inject
    private UploadManager upManager;
    
    @Inject
    private Configuration config;
            
    @PostConstruct
    public void init() {
        LocalDateTime now = LocalDateTime.now();
        
        String date = now.format(TODAY);
        
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Lugbara");
        languages.add("Kakwa");
        
        upload = new UploadNews();
        
        config.printConfig();
        System.out.println("test");
    }
    
    private File saveFile(UploadedFile file) throws Exception {
        if (file.getFileName().isEmpty()) {
            return null;
        }
        File savedFile = new File(config.getNewNewsDestinationFolder(), file.getFileName());
        System.out.println("destination: " + savedFile.getAbsolutePath());
        if (!savedFile.getParentFile().isDirectory()) {
            boolean dirCreated = savedFile.getParentFile().mkdirs();
            System.out.println(dirCreated + ": " + savedFile.getParentFile());
        } else {
            System.out.println(savedFile.getParentFile() + " is a directory");
        }
        //file.write(savedFile.getAbsolutePath()); // not working in payara 4.1.1.154
        Files.copy(file.getInputstream(), Paths.get(savedFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("file saved: " + savedFile.getAbsolutePath());
        
        return savedFile;
    }
    
    public Object save() throws Exception {
        System.out.println("Date:     " + upload.getUploadDate());
        System.out.println("Language: " + upload.getLanguage());
        System.out.println("title:    " + upload.getTitle());
        System.out.println("file:     " + file);
        System.out.println("test:     " + file);
        
        File savedFile = saveFile(file);
        if (savedFile == null) {
            showValidaionError("you have forgotten to add a file");
        } else {
            UploadNews up = new UploadNews(upload.getUploadDate(), upload.getTitle(), savedFile.getName());
            up.setLanguage(upload.getLanguage());
            up.setFilePath(savedFile.getAbsolutePath());
            LocalDateTime now = LocalDateTime.now();
            up.setUploadTime(now.format(NOW));
            
            long size = savedFile.length();
            System.out.println("sie: " + size);
            up.setSizeInByte(size);
            
            upManager.addUpload(up);   
        }
        
        return null;
    }
    
    public void showValidaionError(String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
        FacesContext.getCurrentInstance().addMessage("", message);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<Upload> getUploads() {
        return upManager.getUploads();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadNews getUpload() {
        return upload;
    }

    public void setUpload(UploadNews upload) {
        this.upload = upload;
    }
    
    public StreamedContent download(String fileWithPath) throws FileNotFoundException {
        FileInputStream fis = null;
        System.out.println("---- download: " + fileWithPath);
        File downloadFile = new File(fileWithPath);
        fis = new FileInputStream(downloadFile);
        StreamedContent stream = new DefaultStreamedContent(fis, "application/mp3", downloadFile.getName());
        return stream;
    }
    
}
