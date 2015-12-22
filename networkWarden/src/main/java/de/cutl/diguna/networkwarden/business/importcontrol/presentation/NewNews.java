package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.Configuration;
import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chris
 */
@Model
public class NewNews {
    
    private Upload upload;
    
    private List<String> languages;
    
    private UploadedFile file;
    
    @Inject
    private UploadManager upManager;
    
    @Inject
    private Configuration config;
            
    @PostConstruct
    public void init() {
        String date = "2015_12_21";
        
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Lugbara");
        languages.add("Kakwa");
        
        upload = new Upload(date, null, null, null);
        
        config.printConfig();
        System.out.println("test");
    }
    
    private File saveFile(UploadedFile file) throws Exception {
        if (file.getFileName().isEmpty()) {
            return null;
        }
        File savedFile = new File(config.getNewNewsDestinationFolder(), file.getFileName());
        file.write(savedFile.getAbsolutePath());
        
        return savedFile;
    }
    
    public Object save() throws Exception {
        System.out.println("Date:     " + upload.getUploadDate());
        System.out.println("Language: " + upload.getLanguage());
        System.out.println("title:    " + upload.getTitle());
        System.out.println("file:     " + file);
        
        File savedFile = saveFile(file);
        if (savedFile == null) {
            showValidaionError("you have forgotten to add a file");
        } else {
            Upload up = new Upload(upload.getUploadDate(), upload.getLanguage(), upload.getTitle(), savedFile.getAbsolutePath());
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
    
    public void handleFileUpload(FileUploadEvent event) {
        
        System.out.println("Date:     " + upload.getUploadDate());
        System.out.println("Language: " + upload.getLanguage());
        System.out.println("title:    " + upload.getTitle());
        
        try {
            UploadedFile file = event.getFile();
            File savedFile = new File(file.getFileName());
            file.write(savedFile.getAbsolutePath());
            
            //uploads.add(savedFile.getAbsolutePath());
            
            System.out.println("saved file: " + savedFile.getAbsolutePath());
            
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
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
