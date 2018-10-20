/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author c1041184
 */
@Entity
@Table(name = "TICKET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
    , @NamedQuery(name = "Ticket.findByTicketid", query = "SELECT t FROM Ticket t WHERE t.ticketid = :ticketid")
    , @NamedQuery(name = "Ticket.findByTicketmasterid", query = "SELECT t FROM Ticket t WHERE t.ticketmasterid = :ticketmasterid")
    , @NamedQuery(name = "Ticket.findByTitle", query = "SELECT t FROM Ticket t WHERE t.title = :title")
    , @NamedQuery(name = "Ticket.findByDescription", query = "SELECT t FROM Ticket t WHERE t.description = :description")
    , @NamedQuery(name = "Ticket.findByCreationdate", query = "SELECT t FROM Ticket t WHERE t.creationdate = :creationdate")
    , @NamedQuery(name = "Ticket.findByCloseddate", query = "SELECT t FROM Ticket t WHERE t.closeddate = :closeddate")
    , @NamedQuery(name = "Ticket.findByHandlerid", query = "SELECT t FROM Ticket t WHERE t.handlerid = :handlerid")
    , @NamedQuery(name = "Ticket.findByUseraccountid", query = "SELECT t FROM Ticket t WHERE t.useraccountid = :useraccountid")
    , @NamedQuery(name = "Ticket.findByTicketstatusid", query = "SELECT t FROM Ticket t WHERE t.ticketstatusid = :ticketstatusid")
    , @NamedQuery(name = "Ticket.findByTickettypeid", query = "SELECT t FROM Ticket t WHERE t.tickettypeid = :tickettypeid")
    , @NamedQuery(name = "Ticket.findByTicketseverityid", query = "SELECT t FROM Ticket t WHERE t.ticketseverityid = :ticketseverityid")
    , @NamedQuery(name = "Ticket.findByTicketpriorityid", query = "SELECT t FROM Ticket t WHERE t.ticketpriorityid = :ticketpriorityid")
    , @NamedQuery(name = "Ticket.findByAssetid", query = "SELECT t FROM Ticket t WHERE t.assetid = :assetid")
    , @NamedQuery(name = "Ticket.findByVersion", query = "SELECT t FROM Ticket t WHERE t.version = :version")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKETID")
    private BigDecimal ticketid;
    @Column(name = "TICKETMASTERID")
    private BigInteger ticketmasterid;
    @Size(max = 200)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Column(name = "CLOSEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeddate;
    @Column(name = "HANDLERID")
    private BigInteger handlerid;
    @Column(name = "USERACCOUNTID")
    private BigInteger useraccountid;
    @Column(name = "TICKETSTATUSID")
    private BigInteger ticketstatusid;
    @Column(name = "TICKETTYPEID")
    private BigInteger tickettypeid;
    @Column(name = "TICKETSEVERITYID")
    private BigInteger ticketseverityid;
    @Column(name = "TICKETPRIORITYID")
    private BigInteger ticketpriorityid;
    @Column(name = "ASSETID")
    private BigInteger assetid;
    @Column(name = "VERSION")
    private BigInteger version;

    public Ticket() {
    }

    public Ticket(BigDecimal ticketid) {
        this.ticketid = ticketid;
    }

    public BigDecimal getTicketid() {
        return ticketid;
    }

    public void setTicketid(BigDecimal ticketid) {
        this.ticketid = ticketid;
    }

    public BigInteger getTicketmasterid() {
        return ticketmasterid;
    }

    public void setTicketmasterid(BigInteger ticketmasterid) {
        this.ticketmasterid = ticketmasterid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(Date closeddate) {
        this.closeddate = closeddate;
    }

    public BigInteger getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(BigInteger handlerid) {
        this.handlerid = handlerid;
    }

    public BigInteger getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(BigInteger useraccountid) {
        this.useraccountid = useraccountid;
    }

    public BigInteger getTicketstatusid() {
        return ticketstatusid;
    }

    public void setTicketstatusid(BigInteger ticketstatusid) {
        this.ticketstatusid = ticketstatusid;
    }

    public BigInteger getTickettypeid() {
        return tickettypeid;
    }

    public void setTickettypeid(BigInteger tickettypeid) {
        this.tickettypeid = tickettypeid;
    }

    public BigInteger getTicketseverityid() {
        return ticketseverityid;
    }

    public void setTicketseverityid(BigInteger ticketseverityid) {
        this.ticketseverityid = ticketseverityid;
    }

    public BigInteger getTicketpriorityid() {
        return ticketpriorityid;
    }

    public void setTicketpriorityid(BigInteger ticketpriorityid) {
        this.ticketpriorityid = ticketpriorityid;
    }

    public BigInteger getAssetid() {
        return assetid;
    }

    public void setAssetid(BigInteger assetid) {
        this.assetid = assetid;
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
        hash += (ticketid != null ? ticketid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.ticketid == null && other.ticketid != null) || (this.ticketid != null && !this.ticketid.equals(other.ticketid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ticket[ ticketid=" + ticketid + " ]";
    }
    
}
