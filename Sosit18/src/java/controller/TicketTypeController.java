/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import dao.TickettypeFacade;
import entity.Tickettype;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Vincent
 */
@Named(value = "ticketTypeController")
@SessionScoped
public class TicketTypeController implements Serializable{

    @EJB
    private TickettypeFacade tickettypeFacade;
    
    private Tickettype tickettype = new Tickettype();
    
    public TicketTypeController() {
    }


    public Tickettype getTickettype() {
        return tickettype;
    }

    public void setTickettype(Tickettype tickettype) {
        this.tickettype = tickettype;
    }
    
    public List<Tickettype> listAllTickettypes(){
        int i =0;
        List<Tickettype> ltt = this.tickettypeFacade.GetAllTickettypes();
        return ltt;
    }
    
}