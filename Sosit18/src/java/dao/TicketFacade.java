/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TicketFilterBean;
import entity.Ticket;
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
public class TicketFacade extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }
    
        public Ticket FindById(BigDecimal id){
        Query q = this.em.createNamedQuery("Ticket.findByTicketid");
        q.setParameter("ticketid", id);
        Ticket t = (Ticket)q.getSingleResult();
        return t;
    }
    
    public List<Ticket> GetAllTickets() {
        Query q = this.em.createNamedQuery("Ticket.findAll");
        List<Ticket> l = (List<Ticket>)q.getResultList();
        return l;
    }
    
    public List<Ticket> GetfilteredTickets(TicketFilterBean ticketfilter){
        //if (ticketfilter.getTicketstatus()!=null) {
        //    Query q = this.em.createNamedQuery("Ticket.findByfilter");
        //    q.setParameter("ticketstatusid", ticketfilter.getTicketstatus());
        //    q.setParameter("useraccountid", ticketfilter.getUseraccount());
        //    List<Ticket> l = (List<Ticket>)q.getResultList();
        //    return l;
        //}else{
        //    return this.GetAllTickets();
        //}
        
        String queryParameter = "";
        if (ticketfilter.getTicketstatus().getTicketstatusid()!=null) {
            queryParameter=addQueryParameter(queryParameter,"TICKETSTATUSID",ticketfilter.getTicketstatus().getTicketstatusid());
        }
        if (ticketfilter.getCompany().getCompanyid()!=null) {
            queryParameter=addQueryParameter(queryParameter,"",ticketfilter.getCompany().getCompanyid());
        }
        if (ticketfilter.getUseraccount().getUseraccountid()!=null) {
            queryParameter=addQueryParameter(queryParameter,"USERACCOUNTID",ticketfilter.getUseraccount().getUseraccountid());
        }
        String sqlstring="SELECT * FROM TICKET" + queryParameter;
        Query q = this.em.createNativeQuery(sqlstring);
        List<Ticket> l = (List<Ticket>)q.getResultList();
        return  l;
    }
    

    
    private String addQueryParameter(String queryParameter, String qfield,BigDecimal qvalue ){
        if (!queryParameter.equals("")) {
            queryParameter +=" and ";
        }else{
            queryParameter +=" where ";
        }
        queryParameter+= qfield + "=" + qvalue;
        return qvalue.toString() ;
    }
    
    public List<Ticket> test(){
        String sqlstring = "SELECT * FROM TICKET WHERE TICKETSTATUSID=2 ";
        Query q = this.em.createNativeQuery(sqlstring);
        List<Ticket> l = (List<Ticket>)q.getResultList();
        return l;
    
    
    }

   
}
