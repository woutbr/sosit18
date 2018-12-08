/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Useraccount;
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
public class UseraccountFacade extends AbstractFacade<Useraccount> {

    @PersistenceContext(unitName = "Sosit18PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseraccountFacade() {
        super(Useraccount.class);
    }

    public Useraccount FindByUseraccountid(BigDecimal id) {
        Query q = this.em.createNamedQuery("Useraccount.FindByUseraccountid");
        q.setParameter("Useraccountid", id);
        Useraccount u = (Useraccount) q.getSingleResult();
        return u;
    }

    /**
     * Finds a Useraccount with the given username. If no user is found, null is
     * returned.
     *
     * @param username
     * @return A Useraccount with the given username or null if not found.
     */
    public Useraccount findByUsername(String username) {
        Query q = this.em.createNamedQuery("Useraccount.findByUsername");
        q.setParameter("username", username);
        Useraccount u = (Useraccount) q.getResultList().stream().findFirst().orElse(null);
        return u;
    }

    /**
     * Finds a Useraccount with the given username and password. 
     * If no user is found, null is returned.
     * 
     * @param username
     * @param password
     * @return A Useraccount with the given username and password
     * or null if not found.
     */
    public Useraccount findByUsernamePassword(String username, String password) {
        Useraccount u = this.findByUsername(username);
        if (u != null) {
            String hashedPassword = this.hashPassword(password);
            if (u.getPassword().equals(hashedPassword)) {
                return u;
            }
        }
        //Tegen timing attacks, hash het password
        this.hashPassword(password);
        return null;
    }

    /**
     * TODO hash password. Moet dezelfde functie zijn als bij registratie
     *
     * @param password
     * @return A string of the hashed password.
     */
    private String hashPassword(String password) {
        return password;
    }

    public List<Useraccount> GetAllUsers() {
        Query q = this.em.createNamedQuery("Useraccount.findAll");
        List<Useraccount> l = (List<Useraccount>) q.getResultList();
        return l;
    }
}
