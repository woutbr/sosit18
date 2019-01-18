/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UseraccountFacade;
import entity.Company;
import entity.Useraccount;
import entity.Useraccountrole;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import static java.util.Collections.list;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Inject;

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
    
    @Inject
    private UseraccountRoleController useraccountRoleController;
    

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
            //Wanneer er geen Id wordt meegeven wordt een nieuw user en nieuwe accountrole gecreerd;
            resetUseraccount();
            useraccountRoleController.resetUseraccountrole();

            
        }else{
            useraccount = this.useraccountFacade.FindByUseraccountId(id);
        } 
    }
    
    public List<Useraccount> findAllUsers(){
        //return this.useraccountFacade.findAll();
        // ondertsaande is beter -> querie is gesorteerd
        return this.useraccountFacade.GetAllUsers();
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
        
  
    public String cancel(){
        int a = 1;
        return "userlist?faces-redirect=true";
    }

    public String edit(Useraccount u){
        this.useraccount=u;
        return "useraccount?faces-redirect=true";
    }
    
//    public String create(){
//        useraccount = new Useraccount();
//        return "useraccount?faces-redirect=true";
//    }

    
    public String erase(Useraccount u){
        this.useraccountRoleController.eraseUserAccountroleforUser(u);
        this.useraccountFacade.remove(u);
        return "userlist?faces-redirect=true";
    }
    
    public void resetUseraccount(){
        this.useraccount=new Useraccount();
    }
    
    public String save(){
 
        if (useraccount.getUseraccountid()==null) {
            // een User die nog geen nummer heeft moet een nieuwe user zijn
            this.useraccountFacade.create(useraccount);

            BigDecimal test = useraccount.getUseraccountid();
            int a =1;
            Useraccountrole r = this.useraccountRoleController.getUseraccountrole();
            r.setUseraccountid(useraccount);
            this.useraccountRoleController.setUseraccountrole(r);
            this.useraccountRoleController.createUseraccountrole();
            
            
        }else{
            // een bestaand ticket wordt enkel geupdate
            this.useraccountFacade.edit(useraccount);
            this.useraccountRoleController.updateUserAccountRole();
        }
        
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "userlist?faces-redirect=true";
    }

    
    
    public boolean canDeleteUser(Useraccount u){
        if (!u.getUseraccountroleCollection().isEmpty()){
            return false;
       }
        return true;
    }

    
}



