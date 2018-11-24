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
    private boolean isNewTickettype=false;

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
        setmode("normal");
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
        int a= 0;
        setmode("edit");
        this.tickettype = (Tickettype)tt;
        ticketypeIdSelected=tt.getTickettypeid();
    } 
    

    
    public boolean isRowEditable(BigDecimal id){
        int a = 1;
        return ticketypeIdSelected!=null && ticketypeIdSelected==id && editmode;
    }
    
    public void saveTickettype(){

        if (tickettype.getTickettypeid()==null) {
            this.tickettypeFacade.create(this.tickettype);
        }else{
            this.tickettypeFacade.edit(this.tickettype);
        }
        setmode("normal");
    }
    
    public void deleteTickettype(Tickettype tt){
        this.tickettypeFacade.remove(tt);
        setmode("normal");
        
    }

    public boolean isIsNewTickettype() {
        return isNewTickettype;
    }
    
    public void newTickettype(){
        setmode("new");
        this.tickettype = new Tickettype();    
    }
    
    public void cancel(){
        int a= 0;
        setmode("normal");
    }
    
    public void setmode(String mode){
        // Methode om de zichtbaarheid te suren aan de hand van setmode
        switch (mode) {
                case "edit":
                        isNewTickettype=false;
                        editmode=true;
                    break;
                case "new":
                        isNewTickettype=true;
                        editmode=false;
                    break;
                case "normal":
                        isNewTickettype=false;
                        editmode=false;
                    break;
            }
    
    
    }
    
 

}