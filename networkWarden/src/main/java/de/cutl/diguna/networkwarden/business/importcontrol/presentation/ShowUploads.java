package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadMusic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chris
 */
@Model
public class ShowUploads {

    @Inject
    private UploadManager upManager;
    
    public List<Upload> getUploads() {
        return upManager.getUploads();
    }
    
    public boolean showDownload(Upload upload) {
        System.out.println("showDownload " + upload);
        if (upload instanceof UploadMusic) {
            return false;
        }
        return true;
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
