/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActionFacade;
import dao.TicketFacade;
import entity.Action;
import entity.Ticket;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import javax.ejb.EJB;
import java.util.List;
import javax.inject.Inject;
import helper.Helper;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author Shari
 */
@Named(value = "actionController")
@SessionScoped
public class ActionController implements Serializable {

    @EJB
    private ActionFacade actionFacade;
    @EJB
    private TicketFacade ticketFacade;
    
    @Inject
    private AuthBean authBean;
    
    
    private Action action = new Action();
    


    public Action getAction() {
        return action;
    }
    
    public void setAction(Action action) {
        this.action = action;
    }
    
    public ActionController() {
    }
    
    public void FindById(BigDecimal id){
        action = this.actionFacade.FindById(id);
    }
       
    public String create(){
        if (this.action.getActionid()==null) {
            this.actionFacade.create(action);
            resetAction();
        }else{
            this.actionFacade.edit(action);
        }
   
        return "ticketList?faces-redirect=true";
    }
    

        public void onload(BigDecimal actionId,BigDecimal ticketId){
            if (actionId!=null) {
                this.action=this.actionFacade.FindById(actionId);
            }else{
                    resetAction();
                    this.action.setCreationdate(Helper.findCurrentDateTime());
                    this.action.setTicketid(this.ticketFacade.FindById(ticketId));                    
                    this.action.setUseraccountid(this.authBean.getUser());
            }
    }
    
    public List<Action> GetAllActions(){
        return this.actionFacade.GetAllActions();

    }
    
    public List<Action> listAllActionsByTicket(BigDecimal id){
        
        List<Action> l = this.actionFacade.listActionsByTicketId(id);
        return l;        
    }
    
    public String cancel(){
        return "actionList";
    }
    
    public void resetAction(){
        this.action = new Action();
    } 

}
