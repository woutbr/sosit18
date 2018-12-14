package controller.Roles;

import controller.AuthBean;
import dao.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author woutbr@student.hik.be
 */
public interface RoleFacadeDecider<T>{
    public List<T> listAll(AbstractFacade<T> facade, AuthBean authBean);
    public T findByID(AbstractFacade<T> facade, AuthBean authBean, BigDecimal id) ;
}
