package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chris
 */
@Model
public class NewNews {

    private String date;
    
    private String languageSelect;
    
    private List<String> languages;
    
    private String title;
    
    private List<String> uploads;
    
    private UploadedFile file;
            
    @PostConstruct
    public void init() {
        date = "2015_12_21";
        
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Lugbara");
        languages.add("Kakwa");
        
        uploads = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Object save() throws Exception {
        System.out.println("Date:     " + date);
        System.out.println("Language: " + languageSelect);
        System.out.println("title:    " + title);
        System.out.println("file:     " + file);
        
        File savedFile = new File(file.getFileName());
        file.write(savedFile.getAbsolutePath());
        
        return null;
    }

    public void setLanguageSelect(String languageSelect) {
        this.languageSelect = languageSelect;
    }

    public String getLanguageSelect() {
        return languageSelect;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        
        System.out.println("Date:     " + date);
        System.out.println("Language: " + languageSelect);
        System.out.println("title:    " + title);
        
        try {
            UploadedFile file = event.getFile();
            File savedFile = new File(file.getFileName());
            file.write(savedFile.getAbsolutePath());
            
            uploads.add(savedFile.getAbsolutePath());
            
            System.out.println("saved file: " + savedFile.getAbsolutePath());
            
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getUploads() {
        return uploads;
    }

    public void setUploads(List<String> uploads) {
        this.uploads = uploads;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
    
}
