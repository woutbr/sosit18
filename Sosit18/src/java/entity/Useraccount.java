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
import java.util.function.Predicate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author c1041184
 */
@Entity
@Table(name = "USERACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useraccount.findAll", query = "SELECT u FROM Useraccount u")
    , @NamedQuery(name = "Useraccount.findByUseraccountid", query = "SELECT u FROM Useraccount u WHERE u.useraccountid = :useraccountid")
    , @NamedQuery(name = "Useraccount.findByFirstname", query = "SELECT u FROM Useraccount u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Useraccount.findByLastname", query = "SELECT u FROM Useraccount u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Useraccount.findByUsername", query = "SELECT u FROM Useraccount u WHERE u.username = :username")
    , @NamedQuery(name = "Useraccount.findByPassword", query = "SELECT u FROM Useraccount u WHERE u.password = :password")
    , @NamedQuery(name = "Useraccount.findByUsernamePassword", query = "SELECT u FROM Useraccount u WHERE u.username = :username AND u.password = :password")
    , @NamedQuery(name = "Useraccount.findByEmail", query = "SELECT u FROM Useraccount u WHERE u.email = :email")
    , @NamedQuery(name = "Useraccount.findByPhone", query = "SELECT u FROM Useraccount u WHERE u.phone = :phone")
    , @NamedQuery(name = "Useraccount.findBySex", query = "SELECT u FROM Useraccount u WHERE u.sex = :sex")
    , @NamedQuery(name = "Useraccount.findByVersion", query = "SELECT u FROM Useraccount u WHERE u.version = :version")
    , @NamedQuery(name = "Useraccount.findByCompanyId", query = "SELECT u FROM Useraccount u WHERE u.companyid.companyid = :companyid")})

public class Useraccount implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
//    @Basic(optional = false)
//    @NotNull
    
    @SequenceGenerator(name="USER_SEQ",sequenceName="USER_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "TICKET_SEQ")
    
    @Column(name = "USERACCOUNTID")
    private BigDecimal useraccountid;
    @Size(max = 100)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 200)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 8, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "EMAIL")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "SEX")
    private Character sex;
    @Column(name = "VERSION")
    private BigInteger version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useraccountid")
    private Collection<Useraccountrole> useraccountroleCollection;
    @OneToMany(mappedBy = "senderid")
    private Collection<Message> messageCollection;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private Company companyid;
    @OneToMany(mappedBy = "useraccountid")
    private Collection<Action> actionCollection;
    @OneToMany(mappedBy = "handlerid")
    private Collection<Ticket> ticketCollection;
    @OneToMany(mappedBy = "useraccountid")
    private Collection<Ticket> ticketCollection1;
    @Transient
    private String Fullname;

    public Useraccount() {
    }

    public Useraccount(BigDecimal useraccountid) {
        this.useraccountid = useraccountid;
    }

    public Useraccount(BigDecimal useraccountid, String username, String password) {
        this.useraccountid = useraccountid;
        this.username = username;
        this.password = password;
    }
    
    public String getFullname() {
        return this.firstname+" "+this.lastname;
    }


    public BigDecimal getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(BigDecimal useraccountid) {
        this.useraccountid = useraccountid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    @XmlTransient
    public Collection<Useraccountrole> getUseraccountroleCollection() {
        return useraccountroleCollection;
    }

    public void setUseraccountroleCollection(Collection<Useraccountrole> useraccountroleCollection) {
        this.useraccountroleCollection = useraccountroleCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public Company getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

    public String getFullname() {
        return this.firstname+" "+this.lastname;
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

    @XmlTransient
    public Collection<Ticket> getTicketCollection1() {
        return ticketCollection1;
    }

    public void setTicketCollection1(Collection<Ticket> ticketCollection1) {
        this.ticketCollection1 = ticketCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useraccountid != null ? useraccountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useraccount)) {
            return false;
        }
        Useraccount other = (Useraccount) object;
        if ((this.useraccountid == null && other.useraccountid != null) || (this.useraccountid != null && !this.useraccountid.equals(other.useraccountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Useraccount[ useraccountid=" + useraccountid + " ]";
    }

    /**
     * Search through useraccountroleCollection, through roleid and 
     * finally permissionCollection, for a Permission for 
     * which permMethod tests positive.
     * @param permMethod A method which has a Permission as a parameter
     * and returns a boolean.
     * @return True when a Permission has been found that returns true.
     */
    private boolean searchPermissions(Predicate<Permission> permMethod) {
        for (Useraccountrole useraccountrole : useraccountroleCollection) {
            for (Permission p : useraccountrole.getRoleid().getPermissionCollection()) {
                if (permMethod.test(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getCanedit() {
        return this.searchPermissions(p -> p.getCanedit() > 0);
    }

    public boolean getCanread() {
        return this.searchPermissions(p -> p.getCanread() > 0);
    }

    public boolean getCaninsert() {
        return this.searchPermissions(p -> p.getCaninsert() > 0);
    }

    public boolean getCandelete() {
        return this.searchPermissions(p -> p.getCandelete() > 0);
    }
    
    public boolean hasRole(Role r){
        return useraccountroleCollection.stream().anyMatch((useraccountrole) -> (useraccountrole.getRoleid().equals(r)));
    }
}
