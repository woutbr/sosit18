/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompanyFacade;
import entity.Asset;
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

    @EJB
    private CompanyFacade companyFacade ;
   
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
    
   
    
    
    public List<Company> GetAllCompanies()
    {
     return this.companyFacade.GetAllCompanies();            
    }
    
   
    
    public String create(){
        company = new Company();
        return "company?faces-redirect=true";
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
    
    public String save(){
 
        if (company.getCompanyid()==null) {
            // een ticket dat nog geen nummer heeft moet een nieuw ticket zijn
            this.companyFacade.create(company);
            company = new Company();
        }else{
            // een bestaand ticket wordt enkel geupdate
            this.companyFacade.edit(company);
        }
        
        company = new Company();
        //  ?faces-redirect=true zorgt ervoor dat de browser url meevolgt
        return "companyList?faces-redirect=true";
    }
    
    public String GoTo(Company c){
       this.company = c;
     return "company?faces-redirect=true";
     }
    
    public String cancel()
    {
    return "companyList?faces-redirect=true";
    }
}
