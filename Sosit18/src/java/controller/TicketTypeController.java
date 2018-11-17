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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    private BigDecimal ticketypeIdSelected = null;
    private boolean editmode = false; 

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
    
    public Tickettype getTickettypebyId(BigDecimal id){
        int i =0;
        Tickettype tt = this.tickettypeFacade.GetTicketTypeById(id);
        return tt;
    }
    
    // bij het starten van een pagina Default parameters instellen
    public void loadPage(){
        // ondertsaande moet beter -< hoe wordt recordId 1 
        // -> Mogelijk dat record id 1 ,niet bestaat
        if(this.tickettype.getTickettypeid()==null){
            this.tickettype= this.getTickettypebyId(BigDecimal.valueOf(1));
        }
        editmode=false;
    }
    
    // wordt even niet gebruikt
    public void onSelect(Tickettype tt){
        //this.tickettype=tt;
    }
    
    //public void setEditRow(BigDecimal id){
    //    ticketypeIdSelected = id;
    //    this.tickettype = this.tickettypeFacade.GetTicketTypeById(id);
    //    editmode = true; 
    //} 
    
    
    public void setEditRow(Tickettype tt){
        
        this.tickettype = (Tickettype)tt;
        ticketypeIdSelected=tt.getTickettypeid();
        editmode = true; 
    } 
    
    public boolean isRowEditable(BigDecimal id){
        int a = 1;
        return ticketypeIdSelected!=null && ticketypeIdSelected==id && editmode;
    }
    
    public void saveTickettype(){
        editmode=false;
        this.tickettypeFacade.edit(this.tickettype);
    }

}