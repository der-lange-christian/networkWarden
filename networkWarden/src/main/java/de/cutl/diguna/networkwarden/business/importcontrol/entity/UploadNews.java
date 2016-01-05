package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;

/**
 *
 * @author chris
 */
@Entity
public class UploadNews extends Upload {


    public UploadNews() {
    }

    public UploadNews(String date, String title, String fileName) {
        super(date, title, fileName);
    }
}
