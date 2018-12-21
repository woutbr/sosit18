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
import static java.util.Collections.list;
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
    
    public void findByUseraccountId (BigDecimal id){
        if (id==null) {
            resetUseraccount();
        }else{
            useraccount = this.useraccountFacade.FindByUseraccountId(id);
        } 
    }
    
    public List<Useraccount> findAllUsers(){
        return this.useraccountFacade.findAll();
    }
    
    public List<Useraccount> ListAllUsersByCompany(Company company){

        return listAllUsersByCompanyId(company.getCompanyid());
    }
    
    public List<Useraccount> listAllUsersByCompanyId(BigDecimal companyId){
        List<Useraccount> userlist;
        
        if(companyId!=null){
            userlist = this.useraccountFacade.GetAllUsersByCompanyId(companyId);
        }else{
            userlist =findAllUsers();
        }
        return userlist;
    }
    
    public List<Useraccount> listAllSupporter(){
        List<Useraccount> supportList = this.useraccountFacade.GetAllSupporters();
        return supportList;
        
    
    }
    
    
    
    
    
    public String test(Company co){
        String s = " test";
        return s;
    
    }
  
    public String cancel(){
        int a = 1;
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