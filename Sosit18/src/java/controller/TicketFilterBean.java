/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Company;
import entity.Ticketstatus;
import entity.Useraccount;
import helper.Helper;
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

    
    private BigDecimal ticketStatusId;
    private BigDecimal companyId;
    private BigDecimal userAccountId;
    private BigDecimal supporterId;
    

    public BigDecimal getTicketStatusId() {
        return ticketStatusId;
    }

    public void setTicketStatusId(BigDecimal ticketStatusId) {
        this.ticketStatusId = ticketStatusId;
    }

    public BigDecimal getCompanyId() {
        return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(BigDecimal userAccountId) {
        this.userAccountId = userAccountId;
    }

    public BigDecimal getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(BigDecimal supporterId) {
        this.supporterId = supporterId;
    }
    

    public TicketFilterBean() {
        this.clearFilterVariables();
    }
    
    public String clearFilter(){
        this.clearFilterVariables();
        return  "ticketList?faces-redirect=true";
    }
    
    private void clearFilterVariables(){
        //ticket filter laat default enkel de open tickets zien;
        this.ticketStatusId=BigDecimal.valueOf(1);
        this.companyId=null;
        this.userAccountId=null;
        this.supporterId=null;
    }
    
    
    public void update(String strTicketstatusId, String strCompanyId, String strUseraccountId,String strSupporterId){
        
        if (!Helper.IsNullOrEmpty(strTicketstatusId)) {
            this.ticketStatusId=new BigDecimal(strTicketstatusId);
        }
        if (!Helper.IsNullOrEmpty(strCompanyId)) {
            this.companyId=new BigDecimal(strCompanyId);
        }
        if (!Helper.IsNullOrEmpty(strUseraccountId)) {
            this.userAccountId=new BigDecimal(strUseraccountId);
        }
        if (!Helper.IsNullOrEmpty(strSupporterId)) {
            this.supporterId=new BigDecimal(strSupporterId);
        }
    }
}
