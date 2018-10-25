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

    public TicketFacade getTicketFacade() {
        return ticketFacade;
    }

    public void setTicketFacade(TicketFacade ticketFacade) {
        this.ticketFacade = ticketFacade;
    }
    
    
    public TicketController() {
    }
    
    public Ticket FindById(BigDecimal id){
        return this.ticketFacade.FindById(id);
    
    }
    

    
}
