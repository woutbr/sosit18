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
    
    public String cancel(){
        return "ticketList";
    }
    
    public String edit(){
        this.ticketFacade.edit(ticket);
        this.ticket= new Ticket();
        return "ticketList?faces-redirect=true";
    }
    
    public String edit(Ticket t){
        this.ticket=t;
        this.ticket= new Ticket();
        return "ticket?faces-redirect=true";
    }
    
    public String create(){
        int i = 0;
        this.ticketFacade.create(ticket);
        this.ticket= new Ticket();
        return "ticket?faces-redirect=true";
    }
    
    //public void resetTicket(){
    //    this.ticket= new Ticket();
    //}
    
    
    
    
}
