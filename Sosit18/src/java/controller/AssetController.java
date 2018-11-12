package controller;

import dao.AssetFacade;
import entity.Asset;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Model Managed-Bean
 * Distinctions between different kinds of JSF managed beans:
 * https://stackoverflow.com/a/1030196
 * @author woutbr@student.hik.be
 */
@Named(value = "AssetController")
@SessionScoped
public class AssetController implements Serializable{ 
    @EJB
    private AssetFacade assetFacade;
    
    private Asset asset = new Asset();

    public AssetController() {
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    
    public List<Asset> getAllAssets(){
        return this.assetFacade.findAll();
    }
    
    public Asset findByID(BigDecimal id){
        return this.assetFacade.find(id);
    }

}
