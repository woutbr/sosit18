/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ticketpriority;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author c1041184
 */
@Stateless
public class TicketpriorityFacade extends AbstractFacade<Ticketpriority> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketpriorityFacade() {
        super(Ticketpriority.class);
    }
    
}
