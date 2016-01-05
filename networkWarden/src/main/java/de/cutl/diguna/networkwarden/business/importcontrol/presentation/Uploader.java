package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.Configuration;
import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
public class Uploader {
    
    protected static final DateTimeFormatter TODAY = DateTimeFormatter.ofPattern("yyyy_MM_dd");
    protected static final DateTimeFormatter NOW = DateTimeFormatter.ofPattern("HH:mm:ss");

    protected UploadedFile file;
    
    @Inject
    protected UploadManager upManager;
    
    @Inject
    protected Configuration config;
    
    
    protected String getToday() {
        LocalDateTime now = LocalDateTime.now();
        
        String date = now.format(TODAY);
        
        return date;
    }
    
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public List<Upload> getUploads() {
        return upManager.getUploads();
    }
    
    protected File saveFile(UploadedFile file, String destinationFolder) throws Exception {
        if (file.getFileName().isEmpty()) {
            return null;
        }
        File savedFile = new File(destinationFolder, file.getFileName());
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
    
    
    
    public void showValidaionError(String id, String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, content, content);
        FacesContext.getCurrentInstance().addMessage("upload_form:" + id, message);
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
