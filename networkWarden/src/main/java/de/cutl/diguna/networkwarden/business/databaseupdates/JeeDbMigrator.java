package de.cutl.diguna.networkwarden.business.databaseupdates;

import de.cutl.diguna.networkwarden.business.importcontrol.Configuration;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
 
@Singleton
@Startup
public class JeeDbMigrator {
    private final Logger log = Logger.getLogger(JeeDbMigrator.class.getName());
 
    
    //@Resource(lookup = "jdbc/__default")
    @Resource(lookup = Configuration.DATA_SOURCE)
    private DataSource dataSource;
 
    @PostConstruct
    private void onStartup() {
        if (dataSource == null) {
            log.severe("no datasource found to execute the db migrations!");
            throw new EJBException(
                    "no datasource found to execute the db migrations!");
        }
 
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        
        // only for first run to clean Database
        //flyway.clean();
        
        for (MigrationInfo i : flyway.info().all()) {
            log.info("migrate task: " + i.getVersion() + " : "
                    + i.getDescription() + " from file: " + i.getScript());
        }
        flyway.migrate();
    }
}
