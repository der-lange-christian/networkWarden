package de.cutl.diguna.networkwarden.business.importcontrol.controller;

import de.cutl.diguna.networkwarden.business.importcontrol.Configuration;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author chris
 */
@Stateless
public class UploadManager {
    
    @PersistenceContext(unitName = Configuration.DATA_BASE)
    EntityManager em;

    public List<Upload> getUploads() {
        return em.createNamedQuery(Upload.findAll, Upload.class).getResultList();
    }
    
    public void addUpload(Upload up) {
        try {
            Set<EntityType<?>> entities = em.getEntityManagerFactory().getMetamodel().getEntities();
            for (EntityType<?> entity : entities) {
                System.out.println("- " + entity.getName());
            }

            this.em.merge(up);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
