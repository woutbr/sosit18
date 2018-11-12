/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompanyFacade;
import entity.Company;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author c1039640
 */
@Named(value = "companyController")

@SessionScoped

public class CompanyController implements Serializable {

    /**
     * Creates a new instance of CompanyController
     */
    public CompanyController() {
    }
    
     private Company company = new Company();

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
     
     

    public CompanyFacade getCompanyFacade() {
        return companyFacade;
    }

    public void setCompanyFacade(CompanyFacade companyFacade) {
        this.companyFacade = companyFacade;
    }
    
    @EJB
    private CompanyFacade companyFacade ;
    
    
    public List<Company> GetAllCompanies()
    {
     return this.companyFacade.GetAllCompanies();            
    }
    
    public String create(){
        this.companyFacade.create(company);
        return "companyList?faces-redirect=true";
    }
    
    public String erase(Company c){
        this.companyFacade.remove(c);
        return "companyList?faces-redirect=true";    
    }
    
    public String edit(Company c){
        // open ticket
        this.company= c;
        return "company?faces-redirect=true";
    }
}
