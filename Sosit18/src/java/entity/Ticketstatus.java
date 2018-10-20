/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author c1041184
 */
@Entity
@Table(name = "TICKETSTATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticketstatus.findAll", query = "SELECT t FROM Ticketstatus t")
    , @NamedQuery(name = "Ticketstatus.findByTicketstatusid", query = "SELECT t FROM Ticketstatus t WHERE t.ticketstatusid = :ticketstatusid")
    , @NamedQuery(name = "Ticketstatus.findByDescription", query = "SELECT t FROM Ticketstatus t WHERE t.description = :description")
    , @NamedQuery(name = "Ticketstatus.findByVersion", query = "SELECT t FROM Ticketstatus t WHERE t.version = :version")})
public class Ticketstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKETSTATUSID")
    private BigDecimal ticketstatusid;
    @Size(max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "VERSION")
    private BigInteger version;

    public Ticketstatus() {
    }

    public Ticketstatus(BigDecimal ticketstatusid) {
        this.ticketstatusid = ticketstatusid;
    }

    public BigDecimal getTicketstatusid() {
        return ticketstatusid;
    }

    public void setTicketstatusid(BigDecimal ticketstatusid) {
        this.ticketstatusid = ticketstatusid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketstatusid != null ? ticketstatusid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticketstatus)) {
            return false;
        }
        Ticketstatus other = (Ticketstatus) object;
        if ((this.ticketstatusid == null && other.ticketstatusid != null) || (this.ticketstatusid != null && !this.ticketstatusid.equals(other.ticketstatusid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ticketstatus[ ticketstatusid=" + ticketstatusid + " ]";
    }
    
}
