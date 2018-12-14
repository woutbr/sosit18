package controller.Roles;

import controller.AuthBean;
import dao.AbstractFacade;
import entity.Asset;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wout3
 */
public class AssetAdminFacadeDecider implements RoleFacadeDecider<Asset>{

    @Override
    public List<Asset> listAll(AbstractFacade<Asset> facade, AuthBean authBean) {
        return facade.findAll();
    }

    @Override
    public Asset findByID(AbstractFacade<Asset> facade, AuthBean authBean, BigDecimal id) {
        return facade.find(id);
    }

}
