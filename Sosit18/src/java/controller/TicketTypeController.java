/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import dao.TickettypeFacade;
import entity.Tickettype;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Vincent
 */
@Named(value = "ticketTypeController")
@Dependent
public class TicketTypeController {

    @EJB
    private TickettypeFacade tickettypeFacade;
    
    private Tickettype tickettype;
    
    public TicketTypeController() {
    }


    public Tickettype getTickettype() {
        return tickettype;
    }

    public void setTickettype(Tickettype tickettype) {
        this.tickettype = tickettype;
    }
    
    public List<Tickettype> ListAllTickettypes(){
        return this.tickettypeFacade.GetAllTickettypes();
    }
    
}
