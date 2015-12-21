package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.Pages;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

/**
 *
 * @author chris
 */
@Model
public class Index {
    
    String test;

    @PostConstruct
    public void init() {
        test = "test " + System.currentTimeMillis();
    }

    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
        System.out.println("test: " + this.test);
    }
    
    public Object news() {
        return Pages.NewNews.getPageName();
    }
    
    public Object talkshow() {
        return Pages.NewTalkshow.getPageName();
    }
    
    public Object advertisement() {
        return Pages.NewAdvertisement.getPageName();
    }
    
    public Object music() {
        return Pages.NewMusic.getPageName();
    }
    
    public Object save() {
        return null;
    }
}
