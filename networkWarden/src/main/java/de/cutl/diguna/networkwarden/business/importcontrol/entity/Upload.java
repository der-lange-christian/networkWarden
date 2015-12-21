package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

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
    private String date;    
    private String language;
    private String title;
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
    
    
}
