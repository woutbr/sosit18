/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UseraccountFacade;
import entity.Company;
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
    
    public UserController() {
    }

    public Useraccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }
    
    public void FindByUseraccountid (BigDecimal id){
        useraccount = this.useraccountFacade.FindByUseraccountid(id);
    }
    
    public List<Useraccount> GetAllUsers(){
        return this.useraccountFacade.GetAllUsers();
    }
    
    public List<Useraccount> ListAllUsers(){
        return this.useraccountFacade.GetAllUsers();
    }
    
    public List<Useraccount> ListAllUsersByCompany(Company company){
        List<Useraccount> userlist;
        
        if(company!=null){
            userlist = this.useraccountFacade.GetAllUsersByCompanyId(company);
        }else{
            userlist =ListAllUsers();
        }
        return userlist;
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
        this.useraccountFacade.create(useraccount);
        return "useraccount?faces-redirect=true";
    }
    
    public String erase(Useraccount u){
        this.useraccountFacade.remove(u);
        return "userlist?faces-redirect=true";
    }
    
    public void resetUseraccount(){
        this.useraccount=new Useraccount();
    }
    
}
