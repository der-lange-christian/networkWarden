package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadNews;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadTalkshow;
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
public class NewTalkshow extends Uploader {

    private List<String> languages;
    
    protected UploadTalkshow upload;

    @PostConstruct
    public void init() {
        String date = getToday();

        languages = config.getLanguages();

        upload = new UploadTalkshow();
        upload.setUploadDate(date);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(UploadTalkshow upload) {
        this.upload = upload;
    }
    
    public Object save() throws Exception {
        
        File savedFile = saveFile(file, config.getNewTalkshowDestinationFolder());
        if (savedFile == null) {
            showValidaionError("file", "you have forgotten to add a file");
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
}
