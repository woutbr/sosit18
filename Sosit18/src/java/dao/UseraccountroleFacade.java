/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Useraccountrole;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author c1041184
 */

@Stateless
public class UseraccountroleFacade extends AbstractFacade<Useraccountrole> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseraccountroleFacade() {
        super(Useraccountrole.class);
    }
   
    public Useraccountrole FindByUseraccountRoleId(BigDecimal id){
        Query q = this.em.createNamedQuery("Useraccountrole.findByUseraccountroleid");
        q.setParameter("useraccountroleid", id);
        Useraccountrole r = (Useraccountrole)q.getSingleResult();
        return r;
    }
}
