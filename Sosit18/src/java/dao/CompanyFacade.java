/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author c1041184
 */
@Stateless
public class CompanyFacade extends AbstractFacade<Company> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyFacade() {
        super(Company.class);
    }
    
    public List<Company> GetAllCompanies() {
        Query q = this.em.createNamedQuery("Company.findAll");
        List<Company> l = (List<Company>)q.getResultList();
        return l;
    }
    
    public Company FindById(BigDecimal id){
        Query q = this.em.createNamedQuery("Company.findByCompanyid");
        q.setParameter("companyid", id);
        Company c = (Company)q.getSingleResult();
        return c;
    }
    
}

    