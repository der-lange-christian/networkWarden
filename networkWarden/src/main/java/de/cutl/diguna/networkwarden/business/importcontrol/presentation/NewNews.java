package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.io.File;
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
            
    @PostConstruct
    public void init() {
        String date = "2015_12_21";
        
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Lugbara");
        languages.add("Kakwa");
        
        upload = new Upload(date, null, null, null);
    }
    
    private File saveFile(UploadedFile file) throws Exception {
        if (file.getFileName().isEmpty()) {
            return new File("");
        }
        File savedFile = new File("/home/chris/tmp/upload/", file.getFileName());
        file.write(savedFile.getAbsolutePath());
        
        return savedFile;
    }
    
    public Object save() throws Exception {
        System.out.println("Date:     " + upload.getDate());
        System.out.println("Language: " + upload.getLanguage());
        System.out.println("title:    " + upload.getTitle());
        System.out.println("file:     " + file);
        
        File savedFile = saveFile(file);
        
        Upload up = new Upload(upload.getDate(), upload.getLanguage(), upload.getTitle(), savedFile.getAbsolutePath());
        
        upManager.addUpload(up);
        
        return null;
    }

    public List<String> getLanguages() {
        return languages;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        
        System.out.println("Date:     " + upload.getDate());
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
    
    
    
}
