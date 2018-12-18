/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TicketFilterBean;
import entity.Ticket;
import helper.Helper;
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

    public List<Ticket> GetfilteredTickets(BigDecimal ticketstatusId, BigDecimal companyId, BigDecimal useraccountId){
        String strQuery = "SELECT t FROM Ticket t";
        String strQueryParameter ="";
        
        if (ticketstatusId!=null) {
            String addParameter="t.ticketstatusid.ticketstatusid = "+ticketstatusId;
            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
        }
        
        if (companyId!=null) {
            String addParameter="t.useraccountid.companyid.companyid = "+companyId;
            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
        }
        
        if (useraccountId!=null) {
            String addParameter="t.useraccountid.useraccountid = "+useraccountId;
            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
        }
        
        strQuery+=strQueryParameter+" order by t.creationdate";
        
        Query q = this.em.createQuery(strQuery);
        List<Ticket> l = (List<Ticket>)q.getResultList();
    
        return l;
    }
    
//     public List<Ticket> GetfilteredTickets(String strTicketstatusId, String strCompanyId, String strUseraccountId){
//        String strQuery = "SELECT t FROM Ticket t";
//        String strQueryParameter ="";
//        
//        if (!Helper.IsNullOrEmpty(strTicketstatusId)) {
//            String addParameter="t.ticketstatusid.ticketstatusid = "+strTicketstatusId;
//            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
//        }
//        
//        if (!Helper.IsNullOrEmpty(strCompanyId)) {
//            String addParameter="t.useraccountid.companyid.companyid = "+strCompanyId;
//            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
//        }
//        
//        if (!Helper.IsNullOrEmpty(strUseraccountId)) {
//            String addParameter="t.useraccountid.useraccountid = "+strUseraccountId;
//            strQueryParameter=updateQueryParameter(strQueryParameter,addParameter);
//        }
//        
//        strQuery+=strQueryParameter+" order by t.creationdate";
//        
//        Query q = this.em.createQuery(strQuery);
//        List<Ticket> l = (List<Ticket>)q.getResultList();
//    
//        return l;
//    }
    
    
    

    
    
    private String updateQueryParameter(String queryParameter, String addParameter){
        if (!queryParameter.equals("")) {
            queryParameter +=" and ";
        }else{
            queryParameter +=" where ";
        }
        queryParameter+= addParameter;
        return queryParameter  ;
    }
    
    public List<Ticket> test(){
        String strQuery = "SELECT t FROM Ticket t WHERE t.ticketstatusid.ticketstatusid = 2";


        Query q = this.em.createQuery(strQuery);
        List<Ticket> l = (List<Ticket>)q.getResultList();
        return l;
    }
    
    

   
}
