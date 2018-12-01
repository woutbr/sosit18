package controller;

import dao.AssetgroupFacade;
import entity.Assetgroup;
import java.io.Serializable;
import java.math.BigDecimal;
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
    private Assetgroup assetgroup = null;

    public AssetgroupController() {
        this.assetgroup = new Assetgroup();
    }

    public Assetgroup getAssetgroup() {
        return assetgroup;
    }

    public void setAssetgroup(Assetgroup assetgroup) {
        this.assetgroup = assetgroup;
    }

    public List<Assetgroup> listAllAssetgroups() {
        return this.assetgroupFacade.findAll();
    }
    
    public Assetgroup findByID(BigDecimal id){
        return this.assetgroupFacade.find(id);
    }
    
    public String createNewAssetgroup(){
        this.assetgroupFacade.edit(assetgroup);
        return "assets?faces-redirect=true";
    }

}
