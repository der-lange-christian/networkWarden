package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadMusic;
import static de.cutl.diguna.networkwarden.business.importcontrol.presentation.Uploader.NOW;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

/**
 *
 * @author chris
 */
@Model
public class NewMusic extends Uploader {

    private List<String> languages;
    
    protected UploadMusic upload;
    
    @PostConstruct
    public void init() {
        String date = getToday();

        languages = config.getLanguages();

        upload = new UploadMusic();
        upload.setUploadDate(date);
    }
    
    public List<String> getLanguages() {
        return languages;
    }

    public UploadMusic getUpload() {
        return upload;
    }

    public void setUpload(UploadMusic upload) {
        this.upload = upload;
    }
    
    public Object save() throws Exception {
        
        File savedFile = saveFile(file, config.getNewNewsDestinationFolder());
        if (savedFile == null) {
            showValidaionError("you have forgotten to add a file");
        } else {
            UploadMusic up = new UploadMusic();
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
}
