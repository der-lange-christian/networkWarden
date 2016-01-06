package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadNews;
import static de.cutl.diguna.networkwarden.business.importcontrol.presentation.Uploader.NOW;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chris
 */
@Model
public class NewNews extends Uploader {

    private List<String> languages;
    
    protected UploadNews upload;

    @PostConstruct
    public void init() {
        String date = getToday();

        languages = config.getLanguages();

        upload = new UploadNews();
        upload.setUploadDate(date);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(UploadNews upload) {
        this.upload = upload;
    }
    
    public Object save() throws Exception {
        
        File savedFile = saveFile(file, config.getNewNewsDestinationFolder());
        if (savedFile == null) {
            showValidaionError("file", "you have forgotten to add a file");
        } else {
            UploadNews up = new UploadNews(upload.getUploadDate(), upload.getTitle(), savedFile.getName());
            up.setLanguage(upload.getLanguage());
            up.setFilePath(savedFile.getAbsolutePath());
            LocalDateTime now = LocalDateTime.now();
            up.setUploadTime(now.format(NOW));
            up.setFileName(savedFile.getName());
            
            long size = savedFile.length();
            System.out.println("sie: " + size);
            up.setSizeInByte(size);
            
            upManager.addUpload(up);   
        }
        
        return null;
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
