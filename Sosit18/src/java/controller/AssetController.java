package controller;

import dao.AbstractFacade;
import dao.AssetFacade;
import entity.Asset;
import entity.Company;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "assetController")
@SessionScoped
public class AssetController extends AbstractController<Asset>{

    @EJB
    private AssetFacade assetFacade;
    private Asset asset;
    private BigDecimal assetidSelected = null;
    private boolean editMode = false;

    public AssetController() {
        this.asset = new Asset();
    }

    @Override
    protected AbstractFacade<Asset> getFacade() {
        return this.assetFacade;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    
    public List<Asset> findAssetsByCompany(Company c) {
        return this.assetFacade.findAssetsByCompany(c);
    }

    public void onload() {
        this.assetidSelected = null;
        this.editMode = false;
    }

    public void setEditRow(Asset a) {
        this.asset = a;
        this.assetidSelected = a.getAssetid();
        this.editMode = true;
    }

    public void cancelEditRow() {
        this.asset = null;
        this.assetidSelected = null;
        this.editMode = false;
    }

    public boolean isRowEditable(Asset a) {
        return assetidSelected != null && assetidSelected == a.getAssetid() && this.editMode;
    }

    public boolean isEditMode() {
        return this.editMode;
    }

    public void saveAsset() {
        this.assetFacade.edit(asset);
        this.cancelEditRow();
    }

    public String createNewAsset() {
        this.assetFacade.edit(asset);
        return "assets?faces-redirect=true";
    }
    
    public String loadCreateAsset(){
        this.setAsset(new Asset());
        return "newasset?faces-redirect=true";
    }
}
