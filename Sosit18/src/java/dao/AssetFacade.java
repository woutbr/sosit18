/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Asset;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
