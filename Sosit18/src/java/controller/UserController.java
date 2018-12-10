/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UseraccountFacade;
import entity.Useraccount;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author c1042286
 */

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UseraccountFacade useraccountFacade;
    
    private Useraccount useraccount = new Useraccount();
    

    public Useraccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }
    
        public UserController() {
    }
    
    public void FindByUseraccountid (BigDecimal id){
        if(id==null){
            resetUseraccount();
        }else{
              useraccount = this.useraccountFacade.FindByUseraccountid(id);
        }
    }
    
    public List<Useraccount> GetAllUsers(){
        return this.useraccountFacade.findAll();
    }
    
    public List<Useraccount> ListAllUsers(){
        return this.useraccountFacade.findAll();
    }
       
    public String cancel(){
        return "userlist?faces-redirect=true";
    }
    
    public String edit(){
        this.useraccountFacade.edit(useraccount);
        return "userlist?faces-redirect=true";
    }
    
    public String edit(Useraccount u){
        this.useraccount=u;
        return "useraccount?faces-redirect=true";
    }
    
    public String create(){
        useraccount = new Useraccount();
        return "useraccount?faces-redirect=true";
    }
    
    public String erase(Useraccount u){
        this.useraccountFacade.remove(u);
        return "userlist?faces-redirect=true";
    }
    
    public void resetUseraccount(){
        this.useraccount=new Useraccount();
    }
    
    public String save(){
 
        if (useraccount.getUseraccountid()==null) {
            // een ticket dat nog geen nummer heeft moet een nieuw ticket zijn
            this.useraccountFacade.create(useraccount);
        }else{
            // een bestaand ticket wordt enkel geupdate
            this.useraccountFacade.edit(useraccount);
        }
        
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "userlist?faces-redirect=true";
    }
}