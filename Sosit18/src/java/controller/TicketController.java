/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import dao.TicketFacade;
import entity.Ticket;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Vincent
 */
@Named(value = "TicketController")
@SessionScoped
public class TicketController implements Serializable {

    /**
     * Creates a new instance of TicketController
     */
    @EJB
    private TicketFacade ticketFacade;
    
    private Ticket ticket = new Ticket();

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public TicketController() {
    }
    
    public void FindById(BigDecimal id){
        
         ticket = this.ticketFacade.FindById(id);
    }
    
    public List<Ticket> GetAllTickets(){
        return this.ticketFacade.GetAllTickets();
    
    
    }
    
    
    
    
   
    

    
}
