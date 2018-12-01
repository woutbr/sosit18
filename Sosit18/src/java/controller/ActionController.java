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
import javax.ejb.EJB;
import java.util.List;


/**
 *
 * @author SBYBI03
 */
@Named(value = "actionController")
@SessionScoped
public class ActionController implements Serializable {

    @EJB
    private ActionFacade actionFacade;
    
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
    
    public String create(Ticket t){
        action.setTicketid(t);
        this.actionFacade.create(action);
        return "action?faces-redirect=true";
    }
    
    public List<Action> GetAllActions(){
        return this.actionFacade.GetAllActions();

    }
    
    public String cancel(){
        return "actionList";
    }
    
    public void resetAction(){
        this.action = new Action();
    } 
}
