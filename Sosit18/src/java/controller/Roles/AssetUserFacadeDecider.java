package controller.Roles;

import controller.AuthBean;
import dao.AbstractFacade;
import dao.AssetFacade;
import entity.Asset;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wout3
 */
public class AssetUserFacadeDecider implements RoleFacadeDecider<Asset>{

    @Override
    public List<Asset> listAll(AbstractFacade<Asset> facade, AuthBean authBean) {
        if (authBean.isLoggedIn()){
            return ((AssetFacade)facade).findAssetsByCompany(authBean.getUser().getCompanyid());
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public Asset findByID(AbstractFacade<Asset> facade, AuthBean authBean, BigDecimal id) {
        return facade.find(id);
    }

}
