package de.cutl.diguna.networkwarden.business.importcontrol.presentation;

import de.cutl.diguna.networkwarden.business.importcontrol.controller.UploadManager;
import de.cutl.diguna.networkwarden.business.importcontrol.entity.Upload;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author chris
 */
@Model
public class ShowUploads {

    @Inject
    private UploadManager upManager;
    
    public List<Upload> getUploads() {
        return upManager.getUploads();
    }
}
