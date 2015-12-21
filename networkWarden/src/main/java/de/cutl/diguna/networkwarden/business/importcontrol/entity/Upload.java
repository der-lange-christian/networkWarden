package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chris
 */
@Entity
@NamedQuery(name = Upload.findAll, query = "SELECT u FROM Upload u")
public class Upload {

    @Id
    @GeneratedValue
    private long id;
    @Size(min=1, max=256)
    private String date;    
    @NotNull
    private String language;
    @Size(min=1, max=256)
    private String title;
    @Size(min=1)
    private String fileName;

    private static final String PREFIX = "networkwarden.business.importcontrol.entity.Upload.";
    public static final String findAll = PREFIX + "findAll";
    
    
    public Upload() {
    }

    public Upload(String date, String language, String title, String fileName) {
        this.date = date;
        this.language = language;
        this.title = title;
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getId() {
        return id;
    }
    
    
}
