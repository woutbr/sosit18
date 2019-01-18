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
        this.resetSla();
    }

    public Sla getSla() {
        return sla;
    }

    public void setSla(Sla sla) {
        this.sla = sla;
    }

    public void onload() {
        this.resetSla();
    }
    private void resetSla() {
        this.setSla(new Sla());
    }

    public List<Sla> listAllSlas() {
        return this.slaFacade.findAll();
    }
    
    public Sla findByID(BigDecimal id){
        return this.slaFacade.find(id);
    }
    
    public String createNewSla(){
        this.slaFacade.edit(sla);
        return "assetgroup?faces-redirect=true";
    }

}
