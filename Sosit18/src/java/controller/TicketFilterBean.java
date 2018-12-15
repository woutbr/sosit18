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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author c1041184
 */
@Named(value = "ticketFilterBean")
@SessionScoped
public class TicketFilterBean implements Serializable {

    private Ticketstatus Ticketstatus = new Ticketstatus();
    private Company company = new Company();
    private Useraccount Useraccount = new Useraccount();
            
    public TicketFilterBean() {
    }

    public Ticketstatus getTicketstatus() {
        return Ticketstatus;
    }

    public void setTicketstatus(Ticketstatus Ticketstatus) {
        this.Ticketstatus = Ticketstatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Useraccount getUseraccount() {
        return Useraccount;
    }

    public void setUseraccount(Useraccount Useraccount) {
        this.Useraccount = Useraccount;
    }
    
    public void ClearFilter(){
        this.setCompany(null);
        this.setTicketstatus(null);
        this.setUseraccount(null);
    }
    
    public void test(){
        String S = "";
    
    }
    
}
