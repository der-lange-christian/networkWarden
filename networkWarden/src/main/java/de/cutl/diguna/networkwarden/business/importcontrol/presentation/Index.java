package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

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
    
    public Object save() {
        return null;
    }
}
