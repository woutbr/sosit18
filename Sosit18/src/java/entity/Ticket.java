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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


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
    , @NamedQuery(name = "Ticket.findByTitle", query = "SELECT t FROM Ticket t WHERE t.title = :title")
    , @NamedQuery(name = "Ticket.findByDescription", query = "SELECT t FROM Ticket t WHERE t.description = :description")
    , @NamedQuery(name = "Ticket.findByCreationdate", query = "SELECT t FROM Ticket t WHERE t.creationdate = :creationdate")
    , @NamedQuery(name = "Ticket.findByCloseddate", query = "SELECT t FROM Ticket t WHERE t.closeddate = :closeddate")
    , @NamedQuery(name = "Ticket.findByAssetid", query = "SELECT t FROM Ticket t WHERE t.assetid = :assetid")
    , @NamedQuery(name = "Ticket.findByVersion", query = "SELECT t FROM Ticket t WHERE t.version = :version")
     ,@NamedQuery(name = "Ticket.findByfilter", query = "SELECT t FROM Ticket t WHERE t.ticketstatusid = :ticketstatusid and t.useraccountid =:useraccountid")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name="TICKET_SEQ",sequenceName="TICKET_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "TICKET_SEQ")
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "TICKETID")
    private BigDecimal ticketid;
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
    @Column(name = "ASSETID")
    private BigInteger assetid;
    @Column(name = "VERSION")
    private BigInteger version;
    @OneToMany(mappedBy = "ticketid")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "ticketid")
    private Collection<Action> actionCollection;
    @OneToMany(mappedBy = "ticketmasterid")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "TICKETMASTERID", referencedColumnName = "TICKETID")
    @ManyToOne
    private Ticket ticketmasterid;
    @JoinColumn(name = "TICKETPRIORITYID", referencedColumnName = "TICKETPRIORITYID")
    @ManyToOne
    private Ticketpriority ticketpriorityid;
    @JoinColumn(name = "TICKETSEVERITYID", referencedColumnName = "TICKETSEVERITYID")
    @ManyToOne
    private Ticketseverity ticketseverityid;
    @JoinColumn(name = "TICKETSTATUSID", referencedColumnName = "TICKETSTATUSID")
    @ManyToOne
    private Ticketstatus ticketstatusid;
    @JoinColumn(name = "TICKETTYPEID", referencedColumnName = "TICKETTYPEID")
    @ManyToOne
    private Tickettype tickettypeid;
    @JoinColumn(name = "HANDLERID", referencedColumnName = "USERACCOUNTID")
    @ManyToOne
    private Useraccount handlerid;
    @JoinColumn(name = "USERACCOUNTID", referencedColumnName = "USERACCOUNTID")
    @ManyToOne
    private Useraccount useraccountid;
    


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

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Action> getActionCollection() {
        return actionCollection;
    }

    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Ticket getTicketmasterid() {
        return ticketmasterid;
    }

    public void setTicketmasterid(Ticket ticketmasterid) {
        this.ticketmasterid = ticketmasterid;
    }

    public Ticketpriority getTicketpriorityid() {
        return ticketpriorityid;
    }

    public void setTicketpriorityid(Ticketpriority ticketpriorityid) {
        this.ticketpriorityid = ticketpriorityid;
    }

    public Ticketseverity getTicketseverityid() {
        return ticketseverityid;
    }

    public void setTicketseverityid(Ticketseverity ticketseverityid) {
        this.ticketseverityid = ticketseverityid;
    }

    public Ticketstatus getTicketstatusid() {
        return ticketstatusid;
    }

    public void setTicketstatusid(Ticketstatus ticketstatusid) {
        this.ticketstatusid = ticketstatusid;
    }

    public Tickettype getTickettypeid() {
        return tickettypeid;
    }

    public void setTickettypeid(Tickettype tickettypeid) {
        this.tickettypeid = tickettypeid;
    }

    public Useraccount getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(Useraccount handlerid) {
        this.handlerid = handlerid;
    }

    public Useraccount getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(Useraccount useraccountid) {
        this.useraccountid = useraccountid;
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
