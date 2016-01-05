package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chris
 */
@Entity
public class UploadMusic extends Upload {

    
    private String albumName;
    private String artist;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    
}
