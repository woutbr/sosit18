/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UseraccountroleFacade;
import entity.Role;
import entity.Useraccount;
import entity.Useraccountrole;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author c1042286
 */
@Named(value = "useraccountRoleController")
@SessionScoped
public class UseraccountRoleController implements Serializable {

       @EJB
       private UseraccountroleFacade useraccountroleFacade;
       private Useraccountrole useraccountrole = new Useraccountrole();

    public Useraccountrole getUseraccountrole() {
        return useraccountrole;
    }

    public void setUseraccountrole(Useraccountrole useraccountrole) {
        this.useraccountrole = useraccountrole;
    }

    public UseraccountRoleController() {
    }
    

    public void findByUseraccountRoleId(BigDecimal id){
        if (id==null){
            this.useraccountrole = new Useraccountrole();
        }else{
            useraccountrole = this.useraccountroleFacade.FindByUseraccountRoleId(id);
        }
    }
    
    public List<Useraccountrole> findAllUseraccountRoles(){
        return this.useraccountroleFacade.findAll();
    }
    
//    public void addUseraccountRole(Useraccount userid,Role roleid){
//        this.useraccountrole = new Useraccountrole();
//        this.useraccountrole.setUseraccountid(userid);
//        this.useraccountrole.setRoleid(roleid);
//    }
    
    public String edit(Useraccountrole r){
        this.useraccountrole=r;
        return "useraccount?faces-redirect=true";
    }
    
    public String create(){
        useraccountrole = new Useraccountrole();
        return "useraccount?faces-redirect=true";
    }
    
    public String erase(Useraccountrole r){
        this.useraccountroleFacade.remove(r);
        return "useraccount?faces-redirect=true";
    }
    
    public String save(){
 
        if (useraccountrole.getUseraccountroleid()==null) {
            // een ticket dat nog geen nummer heeft moet een nieuw ticket zijn
            this.useraccountroleFacade.create(useraccountrole);
        }else{
            // een bestaand ticket wordt enkel geupdate
            this.useraccountroleFacade.edit(useraccountrole);
        }
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "userlist?faces-redirect=true";
    }
    
    
    

}
