/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Assetgroup;
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
public class AssetgroupFacade extends AbstractFacade<Assetgroup> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetgroupFacade() {
        super(Assetgroup.class);
    }
    
    public List<Assetgroup> listAllAssetGroups() {
        Query q = this.em.createNamedQuery("Assetgroup.findAll");
        List<Assetgroup> l = (List<Assetgroup>)q.getResultList();
        return l;
    }    
    
}
