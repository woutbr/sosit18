/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TicketpriorityFacade;
import entity.Ticketpriority;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author c1041184
 */
@Named(value = "ticketpriorityController")
@SessionScoped
public class TicketpriorityController implements Serializable {

    @EJB
    TicketpriorityFacade ticketpriorityFacade;
    Ticketpriority ticketpriority = new Ticketpriority();
    
    public TicketpriorityController() {
    }

    public Ticketpriority getTicketpriority() {
        return ticketpriority;
    }

    public void setTicketpriority(Ticketpriority ticketpriority) {
        this.ticketpriority = ticketpriority;
    }
    
    public List<Ticketpriority> ListTicketpriority(){
        List<Ticketpriority> ltp = this.ticketpriorityFacade.findAll();
        return ltp;
    }
    


}
