/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Company;
import entity.Ticketstatus;
import entity.Useraccount;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author c1041184
 */
@Named(value = "ticketFilterBean")
@SessionScoped
public class TicketFilterBean implements Serializable {

    private Ticketstatus ticketStatus = new Ticketstatus();
    private Company company = new Company();
    private Useraccount userAccount = new Useraccount();
            
    public TicketFilterBean() {
    }

    public Ticketstatus getTicketstatus() {
        return ticketStatus;
    }

    public void setTicketstatus(Ticketstatus Ticketstatus) {
        this.ticketStatus = Ticketstatus;

        
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Useraccount getUseraccount() {
        return userAccount;
    }

    public void setUseraccount(Useraccount Useraccount) {
        this.userAccount = Useraccount;
    }
    
    public String clearFilter(){
        this.setCompany(null);
        this.setTicketstatus(null);
        this.setUseraccount(null);
        return  "ticketList?faces-redirect=true";
    }
    
    public void test(){
        String S = "";
    }
    
    public void init(){
        this.setCompany(null);
        this.ticketStatus.setTicketstatusid(new BigDecimal(1));
        this.setUseraccount(null);
    
    }
    
    
    
}
