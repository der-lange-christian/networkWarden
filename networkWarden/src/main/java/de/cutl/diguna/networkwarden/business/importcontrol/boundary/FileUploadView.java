package de.cutl.diguna.networkwarden.business.importcontrol.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chris
 */
@ManagedBean
public class FileUploadView {

    public void handleFileUpload(FileUploadEvent event) {
        try {
            UploadedFile file = event.getFile();
            
            file.write(event.getFile().getFileName());
            
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() +
                    " is uploaded to ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
