package controller;

import dao.AssetgroupFacade;
import entity.Assetgroup;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author woutbr@student.hik.be
 */
@Named(value = "assetgroupController")
@SessionScoped
public class AssetgroupController implements Serializable {

    @EJB
    private AssetgroupFacade assetgroupFacade;
    private Assetgroup selectedAssetgroup = null;

    public AssetgroupController() {
    }

    public Assetgroup getSelectedAssetgroup() {
        return selectedAssetgroup;
    }

    public void setSelectedAssetgroup(Assetgroup selectedAssetgroup) {
        this.selectedAssetgroup = selectedAssetgroup;
    }

    public List<Assetgroup> getAllAssetgroups() {
        return this.assetgroupFacade.findAll();
    }

}
