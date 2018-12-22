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
import entity.Useraccount;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import helper.Helper;
import javax.inject.Inject;


/**
 *
 * @author Vincent
 */
@Named(value = "TicketController")
@SessionScoped
public class TicketController implements Serializable {


    @EJB
    private TicketFacade ticketFacade;
    
    @Inject
    private AuthBean authBean;
    
    @Inject
    private TicketFilterBean ticketFilterBean;

//    
//    public void setAuth(AuthBean auth) {
//        this.auth = auth;
//    }
        
    private Ticket ticket = new Ticket();

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketController() {

    }

    public void findById(BigDecimal id) {
        if (id == null) {
            resetTicket();
        } else {
            ticket = this.ticketFacade.FindById(id);
        }
    }

    public List<Ticket> findAllTickets() {
        return this.ticketFacade.findAll();
    }

    public List<Ticket> loadfilteredTickets(){

        // gewone users mogen enkel tickets van zichzelf zien
        if (authBean.isGewoneUser()) {
            ticketFilterBean.setUserAccountId(authBean.getUser().getUseraccountid());
        }
        List<Ticket> ticketList = this.ticketFacade.GetfilteredTickets(
                ticketFilterBean.getTicketStatusId(),
                ticketFilterBean.getCompanyId(),
                ticketFilterBean.getUserAccountId());
        
        return ticketList;
    }
    
    


    public String cancel() {
        return "ticketList?faces-redirect=true";
    }

    public String save() {

        if (ticket.getTicketid() == null) {
            // een ticket dat nog geen nummer heeft moet een nieuw ticket zijn

            ticket.setCreationdate(findCurrentDate());
            this.ticketFacade.create(ticket);

        } else {
            // een bestaan ticket wordt enkel geupdate
            this.ticketFacade.edit(ticket);
        }

        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "ticketList?faces-redirect=true";
    }

    public String edit(Ticket t) {
        // open ticket
        this.ticket = t;
        return "ticket?faces-redirect=true";
    }

    public String create() {

        return "ticket?faces-redirect=true";
    }

    public String erase(Ticket t) {
        this.ticketFacade.remove(t);
        return "ticketList?faces-redirect=true";

    }

    public void resetTicket() {
        // wordt aangeroepen in ticketList
        this.ticket = new Ticket();
        ticket.setCreationdate(findCurrentDate());
    }

    public Date findCurrentDate() {
        LocalDate ld = LocalDate.now();
        Date d = java.sql.Date.valueOf(ld);
        return d;
    }
}