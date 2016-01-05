package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.UploadNews;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chris
 */
@Model
public class NewNews extends Uploader {

    private List<String> languages;

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

    public List<Upload> getUploads() {
        return upManager.getUploads();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadNews getUpload() {
        return upload;
    }

    public void setUpload(UploadNews upload) {
        this.upload = upload;
    }

}
