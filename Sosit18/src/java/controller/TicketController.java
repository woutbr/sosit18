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
    private List<Ticket> ticketList = new ArrayList<>();

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketController() {


    }
    
    public void findById(BigDecimal id){
        if (id==null ) {
            resetTicket();
        }else{
            ticket = this.ticketFacade.FindById(id);
        }
    }
    
    public List<Ticket> findAllTickets(){
        return this.ticketFacade.findAll();
    }
    
    public List<Ticket> findFilteredTickets(TicketFilterBean ticketfilter){
        if (ticketfilter!=null) {
            return this.ticketFacade.GetfilteredTickets(ticketfilter);   
        }else{
            return findAllTickets();
        }
            
    }
    
    public String cancel(){
        return "ticketList?faces-redirect=true";
    }
    
    public String save(){
 
        if (ticket.getTicketid()==null) {
            // een ticket dat nog geen nummer heeft moet een nieuw ticket zijn

            ticket.setCreationdate(findCurrentDate());
            this.ticketFacade.create(ticket);

        }else{
            // een bestaan ticket wordt enkel geupdate
            this.ticketFacade.edit(ticket);
        }
        
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "ticketList?faces-redirect=true";
    }
    
    public String edit(Ticket t){
        // open ticket
        this.ticket= t;
        return "ticket?faces-redirect=true";
    }
    
    public String create(){

        return "ticket?faces-redirect=true";
    }
    
    public String erase(Ticket t){
        this.ticketFacade.remove(t);
        return "ticketList?faces-redirect=true";
    
    }
    
    public void resetTicket(){
        // wordt aangeroepen in ticketList
        this.ticket= new Ticket();
        ticket.setCreationdate(findCurrentDate());
        int a = 1;
    }

    public Date findCurrentDate(){
        LocalDate ld  = LocalDate.now();
        Date      d   =java.sql.Date.valueOf(ld);
        return d;
    }
    
    public void test(){
        int a =1;
    }
    
    public void loadTicketList(){
        ticketList=findFilteredTickets(null);
    }        
}
