package controller;

import dao.AbstractFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author woutbr@student.hik.be
 */
public abstract class AbstractController<T> implements Serializable{
    
    protected abstract AbstractFacade<T> getFacade();
    
    public List<T> listAll(){
        return this.getFacade().findAll();
    }

    public T findByID(BigDecimal id) {
        return this.getFacade().find(id);
    }
}
