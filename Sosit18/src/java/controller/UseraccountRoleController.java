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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    
//    public String edit(Useraccountrole r){
//        this.useraccountrole=r;
//        return "useraccount?faces-redirect=true";
//    }
    
//    public String create(){
//        useraccountrole = new Useraccountrole();
//        return "useraccount?faces-redirect=true";
//    }
    
   
    
//    public String erase(Useraccountrole r){
//        this.useraccountroleFacade.remove(r);
//        return "useraccount?faces-redirect=true";
//    }
    
//    public String save(){
// 
//        if (useraccountrole.getUseraccountroleid()==null) {
//            // een Useraccountrole dat nog geen nummer heeft moet een nieuw ticket zijn
//            this.useraccountroleFacade.create(useraccountrole);
//        }else{
//            // een bestaande rol wordt enkel geupdate
//            this.useraccountroleFacade.edit(useraccountrole);
//        }
//        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
//        return "userlist?faces-redirect=true";
//    }
    
    public Useraccountrole findAccountRodeIdByUserId(BigDecimal userId){
        Useraccountrole ur;
        if (userId==null) {
            ur = new Useraccountrole();
            Role r = new Role();
            r.setRoleid(new BigDecimal(1));
            ur.setRoleid(r);

        }else{
            ur = this.useraccountroleFacade.findAccountRodeIdByUserId(userId);
        }

        return ur;
    }
    
    public void updateUserAccountRole(){
        // wanneer een user van rol veranderd;
        Role r = new Role();
        BigDecimal roleid = this.useraccountrole.getRoleid().getRoleid();
        r.setRoleid(roleid);
        this.useraccountrole.setRoleid(r);
        
        // ik begrijp niet waarome bovenstaande nodig is
        // Normaal moet enkel onderste rij alleen ook werken
        this.useraccountroleFacade.edit(useraccountrole);
    }
    
    public void resetUseraccountrole(){
        this.useraccountrole=new Useraccountrole();
        // standaard krijgt een nieuw rol id=1 -> gebruikers
        Role r = new Role();
        r.setRoleid(new BigDecimal(1));;
        this.useraccountrole.setRoleid(r);
    }
    
    public void createUseraccountrole(){
        // bij het make van een nieuwe user, 
        // wordt er automatisch ook een Useraccountrole gecreerd
        this.useraccountroleFacade.create(useraccountrole);
    }
    
    public void eraseUserAccountroleforUser(Useraccount useraccount){
        BigDecimal userid = useraccount.getUseraccountid();
        Useraccountrole u = this.useraccountroleFacade.findAccountRodeIdByUserId(userid);
        this.useraccountroleFacade.remove(u);
 }

}
