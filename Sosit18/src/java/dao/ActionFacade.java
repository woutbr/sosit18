/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Action;
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
public class ActionFacade extends AbstractFacade<Action> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActionFacade() {
        super(Action.class);
    }
    
    public Action FindById(BigDecimal id){
        Query q = this.em.createNamedQuery("Action.findByActionid");
        q.setParameter("actionid", id);
        Action a = (Action)q.getSingleResult();
        return a;
    }
    
    public List<Action> GetAllActions() {
        Query q = this.em.createNamedQuery("Action.findAll");
        List<Action> l = (List<Action>)q.getResultList();
        return l;
    }    
    
    public List<Action> listActionsByTicketId(BigDecimal id){
        Query q = this.em.createNamedQuery("Action.findByTicketId");
        q.setParameter("ticketid",id);
        List<Action> l =(List<Action>)q.getResultList();
        return l;
    }
}
