package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

 
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@Model
public class FileUploadView {
    
    private String type;  
    private Map<String,String> types = new HashMap<String, String>();
    
    @PostConstruct
    public void init() {
        types = new HashMap<String, String>();
        types.put("News", "News");
        types.put("DW", "DW");
        types.put("Adverts", "Adverts");
    }
 
    public void handleFileUpload(FileUploadEvent event) {
        
        try {
            UploadedFile file = event.getFile();
            file.write("target/test.mp3");
            
            
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getTypes() {
        return types;
    }

    public void setTypes(Map<String, String> types) {
        this.types = types;
    }
    
    
}
