package controller;

import controller.Roles.AssetAdminFacadeDecider;
import controller.Roles.RoleFacadeDecider;
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
        this.chooseRoleFacadeDecider();
        this.asset = new Asset();
    }
    
    private void chooseRoleFacadeDecider(){
        RoleFacadeDecider<Asset> roleFacadeDecider = null;
        if(this.authBean.isLoggedIn()){
            if(this.authBean.isAdmin()){
                roleFacadeDecider = new AssetAdminFacadeDecider();
            }else if(this.authBean.isSupporter()){
                roleFacadeDecider = new AssetAdminFacadeDecider();
            }else if(this.authBean.isGewoneUser()){
                roleFacadeDecider = new AssetAdminFacadeDecider();
            }
        }else{
            //TODO Niet ingelogd, throw error
        }
        this.setRoleFacadeDecider(roleFacadeDecider);
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
}
