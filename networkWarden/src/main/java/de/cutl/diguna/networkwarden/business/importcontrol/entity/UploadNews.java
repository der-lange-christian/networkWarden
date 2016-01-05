package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chris
 */
@Entity
public class UploadNews extends Upload {

    @NotNull
    private String language;

    public UploadNews() {
    }

    public UploadNews(String date, String title, String fileName) {
        super(date, title, fileName);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
