/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import entity.Useraccount;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author c1041184
 */
@Stateless
public class UseraccountFacade extends AbstractFacade<Useraccount> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseraccountFacade() {
        super(Useraccount.class);
    }
    
    public Useraccount FindByUseraccountId(BigDecimal id){
        Query q = this.em.createNamedQuery("Useraccount.findByUseraccountid");
        q.setParameter("useraccountid", id);
        Useraccount u = (Useraccount)q.getSingleResult();
        return u;
    }
    
    public List<Useraccount> GetAllUsers(){
        Query q = this.em.createNamedQuery("Useraccount.findAll");
        List<Useraccount> l = (List<Useraccount>)q.getResultList();
        return l;
    }
    
    public List<Useraccount> GetAllUsersByCompanyId(BigDecimal companyId){
        Query q = this.em.createNamedQuery("Useraccount.findByCompanyId");
        q.setParameter("companyid", companyId);
        List<Useraccount> l = (List<Useraccount>)q.getResultList();
        return l;
    }
}
