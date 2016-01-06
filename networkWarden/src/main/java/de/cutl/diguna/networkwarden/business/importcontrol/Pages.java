package de.cutl.diguna.networkwarden.business.importcontrol;

import javax.enterprise.inject.Model;

/**
 *
 * @author chris
 */
@Model
public enum Pages {

    NewIndex,
    NewNews,
    NewTalkshow,
    NewAdvertisement,
    NewMusic,
    ShowUploads,
    user_Index,
    admin_Index;
    
    private final String pageName;
    
    Pages() {
        pageName = this.name().toLowerCase().replace("_", "/");
    }
    
    public String getPageName() {
        return this.pageName;
    }
}
