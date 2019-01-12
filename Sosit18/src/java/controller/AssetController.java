package controller;

import dao.AbstractFacade;
import dao.AssetFacade;
import dao.TicketFacade;
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
    @EJB
    private TicketFacade ticketFacade;
    
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

    public void onloadAsset(BigDecimal id) {
        if(id == null){
            this.resetAsset();
        }else{
            this.setAsset(this.assetFacade.find(id));
        }
    }

    public void setEditRow(Asset a) {
        this.setAsset(a);
        this.assetidSelected = a.getAssetid();
        this.editMode = true;
    }

    public void cancelEditRow() {
        this.resetAsset();
        this.assetidSelected = null;
        this.editMode = false;
    }
    
    private void resetAsset(){
        this.setAsset(new Asset());
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
        return "assetList?faces-redirect=true";
    }
    
    public String loadCreateAsset(){
        this.resetAsset();
        return "asset?faces-redirect=true";
    }
    
    public String erase(Asset a) {
        this.assetFacade.remove(a);
        return "assetList?faces-redirect=true";

    }
    
    /**
     * Checks whether an Asset can be safely deleted.
     * If there are sub-assets, this asset can not be deleted.
     * If there are tickets with this asset, this asset can not be deleted.
     * @param a The Asset to check
     * @return True if the given Asset can be deleted
     */
    public boolean canDeleteAsset(Asset a) {
        if(!a.getAssetCollection().isEmpty()
                || !ticketFacade.getTicketsByAssetid(a.getAssetid()).isEmpty()){
            return false;
        }
        return true;
    }
}
