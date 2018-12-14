package controller;

import controller.Roles.RoleFacadeDecider;
import dao.AbstractFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 * @author woutbr@student.hik.be
 */
public abstract class AbstractController<T> implements Serializable {

    @Inject
    protected AuthBean authBean;
    private RoleFacadeDecider<T> roleFacadeDecider;

    protected abstract AbstractFacade<T> getFacade();

    protected void setRoleFacadeDecider(RoleFacadeDecider<T> roleFacadeDecider) {
        this.roleFacadeDecider = roleFacadeDecider;
    }

    public List<T> listAll() {
        return this.roleFacadeDecider.listAll(this.getFacade(), this.authBean);
    }

    public T findByID(BigDecimal id) {
        return this.roleFacadeDecider.findByID(this.getFacade(), this.authBean, id);
    }
}
