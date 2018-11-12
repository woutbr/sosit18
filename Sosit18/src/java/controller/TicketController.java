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
        // slaat het ticket op in de DB
        this.ticketFacade.edit(ticket);
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "ticketList?faces-redirect=true";
    }
    
    public String edit(Ticket t){
        // open ticket
        this.ticket= t;
        return "ticket?faces-redirect=true";
    }
    
    public String create(){
        this.ticketFacade.create(ticket);
        return "ticket?faces-redirect=true";
    }
    
    public String erase(Ticket t){
        this.ticketFacade.remove(t);
        return "ticketList?faces-redirect=true";
    
    }
    
    public void resetTicket(){
        // wordt aangeroepen in ticketList
        this.ticket= new Ticket();
    }

    
    
}
