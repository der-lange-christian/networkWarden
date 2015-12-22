package de.cutl.diguna.networkwarden.business.importcontrol;

/**
 *
 * @author chris
 */
public enum Pages {

    NewIndex,
    NewNews,
    NewTalkshow,
    NewAdvertisement,
    NewMusic,
    ShowUploads;
    
    private final String pageName;
    
    Pages() {
        pageName = this.name().toLowerCase();
    }
    
    public String getPageName() {
        return this.pageName;
    }
}
