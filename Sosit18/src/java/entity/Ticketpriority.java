/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author c1041184
 */
@Entity
@Table(name = "TICKETPRIORITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticketpriority.findAll", query = "SELECT t FROM Ticketpriority t")
    , @NamedQuery(name = "Ticketpriority.findByTicketpriorityid", query = "SELECT t FROM Ticketpriority t WHERE t.ticketpriorityid = :ticketpriorityid")
    , @NamedQuery(name = "Ticketpriority.findByDescription", query = "SELECT t FROM Ticketpriority t WHERE t.description = :description")
    , @NamedQuery(name = "Ticketpriority.findByVersion", query = "SELECT t FROM Ticketpriority t WHERE t.version = :version")})
public class Ticketpriority implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKETPRIORITYID")
    private BigDecimal ticketpriorityid;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "VERSION")
    private BigInteger version;
    @OneToMany(mappedBy = "ticketpriorityid")
    private Collection<Ticket> ticketCollection;

    public Ticketpriority() {
    }

    public Ticketpriority(BigDecimal ticketpriorityid) {
        this.ticketpriorityid = ticketpriorityid;
    }

    public BigDecimal getTicketpriorityid() {
        return ticketpriorityid;
    }

    public void setTicketpriorityid(BigDecimal ticketpriorityid) {
        this.ticketpriorityid = ticketpriorityid;
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

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketpriorityid != null ? ticketpriorityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticketpriority)) {
            return false;
        }
        Ticketpriority other = (Ticketpriority) object;
        if ((this.ticketpriorityid == null && other.ticketpriorityid != null) || (this.ticketpriorityid != null && !this.ticketpriorityid.equals(other.ticketpriorityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ticketpriority[ ticketpriorityid=" + ticketpriorityid + " ]";
    }
    
}
