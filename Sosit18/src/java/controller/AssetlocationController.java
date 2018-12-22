package controller;

import dao.AssetlocationFacade;
import entity.Assetlocation;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "assetlocationController")
@SessionScoped
public class AssetlocationController implements Serializable {

    @EJB
    private AssetlocationFacade assetlocationFacade;
    private Assetlocation assetlocation;

    public AssetlocationController() {
        this.assetlocation = new Assetlocation();
    }

    public Assetlocation getAssetlocation() {
        return assetlocation;
    }

    public void setAssetlocation(Assetlocation assetlocation) {
        this.assetlocation = assetlocation;
    }

    public List<Assetlocation> listAllAssetlocations() {
        return this.assetlocationFacade.findAll();
    }

    public Assetlocation findByID(BigDecimal id) {
        return this.assetlocationFacade.find(id);
    }

    public String createNewAssetlocation() {
        this.assetlocationFacade.edit(assetlocation);
        return "assets?faces-redirect=true";
    }
}
