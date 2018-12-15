/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.TicketstatusFacade;
import entity.Ticketstatus;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author c1041184
 */
@Named(value = "ticketstatusController")
@SessionScoped
public class TicketstatusController implements Serializable {
    
    @EJB
    private TicketstatusFacade ticketstatusFacade;
    private Ticketstatus ticketstatus = new Ticketstatus();

    public TicketstatusController() {
    }

    public Ticketstatus getTicketstatus() {
        return ticketstatus;
    }

    public void setTicketstatus(Ticketstatus Ticketstatus) {
        this.ticketstatus = Ticketstatus;
    }
    
    public List<Ticketstatus> ListAllTicketstatus(){
        List<Ticketstatus> lts;
        lts = this.ticketstatusFacade.findAll();
        return lts;
    }
    



}
