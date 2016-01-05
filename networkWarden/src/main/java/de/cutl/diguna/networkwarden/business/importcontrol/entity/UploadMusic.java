package de.cutl.diguna.networkwarden.business.importcontrol.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chris
 */
@Entity
public class UploadMusic extends Upload {

    @NotNull
    private String language;
    @NotNull
    private String title;
    @NotNull
    private String albumName;
    @NotNull
    private String artist;
}
