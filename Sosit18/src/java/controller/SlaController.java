package controller;

import dao.SlaFacade;
import entity.Sla;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "slaController")
@SessionScoped
public class SlaController implements Serializable {

    @EJB
    private SlaFacade slaFacade;
    private Sla sla;

    public SlaController() {
        this.sla = new Sla();
    }

    public Sla getSla() {
        return sla;
    }

    public void setSla(Sla sla) {
        this.sla = sla;
    }

    public List<Sla> getAllSlas() {
        return this.slaFacade.findAll();
    }
    
    public Sla findByID(BigDecimal id){
        return this.slaFacade.find(id);
    }
    
    public String createNewSla(){
        this.slaFacade.edit(sla);
        return "assets?faces-redirect=true";
    }

}
