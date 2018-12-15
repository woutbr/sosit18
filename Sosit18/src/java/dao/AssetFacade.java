/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Asset;
import entity.Company;
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
public class AssetFacade extends AbstractFacade<Asset> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetFacade() {
        super(Asset.class);
    }
    
    public List<Asset> findAssetsByCompany(Company c) {
        Query q = this.em.createNamedQuery("Asset.findByCompany");
        q.setParameter("companyid", c);
        List<Asset> l = (List<Asset>)q.getResultList();
        return l;
    }
}
