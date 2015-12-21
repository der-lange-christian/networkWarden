package de.cutl.diguna.networkwarden.business.importcontrol.controller;

import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chris
 */
@Stateless
public class UploadManager {

    @PersistenceContext
    EntityManager em;

    public List<Upload> getUploads() {
        return em.createNamedQuery(Upload.findAll, Upload.class).getResultList();
    }
    
    public void addUpload(Upload up) {
        this.em.merge(up);
    }
}
