package de.cutl.diguna.networkwarden.business.importcontrol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * inspired by
 * 
 * - 
 * @author chris
 */
@Singleton
public class Configuration {
    
    private static final String SYSTEM_PROP_NAME="networkWardenConfig";
    private Properties config;
    
    
    public static final String DB_TEST_DROP_AND_CREATE = "testDropAndCreate";
    public static final String DB_TEST = "test";
    public static final String DB_PROD = "diguna";
    
    
    public static final String DATA_BASE = DB_TEST;
    public static final String DATA_SOURCE = "jdbc/" + DATA_BASE;
    
    
    private List<String> languages;
    

    @PostConstruct
    public void init() {
        loadConfig();
        System.out.println("read config");
    }
    
    private void loadConfig() {
        try {
            config = new Properties();
            String configFile = System.getProperty(SYSTEM_PROP_NAME);
            if (configFile == null) {
                System.out.println("System property is not defined: -D" + SYSTEM_PROP_NAME);
                return;
            }
            if (!new File(configFile).exists()) {
                System.out.println("configFile defined by system property " + SYSTEM_PROP_NAME + " does not exist.");
                System.out.println("configFile: " + configFile);
                return;
            }
            config.load(new FileReader(configFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printConfig() {
        for (String key : config.stringPropertyNames()) {
            System.out.println(key + ": " + config.getProperty(key));
        }
    }
    
    public String getNewNewsDestinationFolder() {
        return config.getProperty("newNewsDestinationFolder", "not configured");
    }
    
    public String getNewAdvertisementDestinationFolder() {
        return config.getProperty("newAdvertisementDestinationFolder", "not configured");
    }
    
    public String getNewMusicDestinationFolder() {
        return config.getProperty("newMusicDestinationFolder", "not configured");
    }
    
    public String getNewTalkshowDestinationFolder() {
        return config.getProperty("newTalkshowDestinationFolder", "not configured");
    }
    
    public String getDatabase() {
        return config.getProperty("database", "prod");
    }

    public List<String> getLanguages() {
        if (this.languages == null) {
            languages = new ArrayList<>();

            int i = 0;
            while (true) {
                String lang = config.getProperty("language" + i, "");
                
                if (lang.isEmpty()) {
                    break;
                } else {
                    languages.add(lang);
                    ++i;
                }
            }
        }
        
        return languages;
    }
}
