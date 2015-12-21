package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

/**
 *
 * @author chris
 */
@Model
public class NewsUpload {
    
    private String date;
    
    final private String pattern        = "yyyy_MM_dd";
    final private DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
    
    @PostConstruct
    public void init() {
        LocalDate today = LocalDate.now();
        date = dtf.format(today);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

}
