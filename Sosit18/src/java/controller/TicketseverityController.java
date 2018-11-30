/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TicketseverityFacade;
import entity.Ticketseverity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Vincent
 */
@Named(value = "ticketseverityController")
@SessionScoped
public class TicketseverityController implements Serializable {

    @EJB
    private TicketseverityFacade ticketseverityFacade;
    private Ticketseverity ticketseverity;
    
    public TicketseverityController() {
    }
    
    public Ticketseverity getTicketseverity() {
        return ticketseverity;
    }

    public void setTicketseverity(Ticketseverity ticketseverity) {
        this.ticketseverity = ticketseverity;
    }
    
    public List<Ticketseverity> getAllTicketSeverity(){
        List<Ticketseverity> lts= this.ticketseverityFacade.findAll();
        return lts;

    }



}
