package de.cutl.diguna.networkwarden.business.importcontrol.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * browser: http://localhost:8080/networkWarden/api/importcontrol
 * 
 * @author chris
 */
@Path("importcontrol")
public class ImportControlResource {
    
    @GET
    public String hello() {
        return "hey " + System.currentTimeMillis();
    }
}
