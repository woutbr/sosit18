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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author c1041184
 */
@Entity
@Table(name = "USERACCOUNTROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useraccountrole.findAll", query = "SELECT u FROM Useraccountrole u")
    , @NamedQuery(name = "Useraccountrole.findByUseraccountroleid", query = "SELECT u FROM Useraccountrole u WHERE u.useraccountroleid = :useraccountroleid")
    , @NamedQuery(name = "Useraccountrole.findByVersion", query = "SELECT u FROM Useraccountrole u WHERE u.version = :version")})
public class Useraccountrole implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
//    @Basic(optional = false)
//    @NotNull
    
    @SequenceGenerator(name="USERACCOUNTROLE_SEQ",sequenceName="USERACCOUNTROLE_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "USERACCOUNTROLE_SEQ")
    
    @Column(name = "USERACCOUNTROLEID")
    private BigDecimal useraccountroleid;
    @Column(name = "VERSION")
    private BigInteger version;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne(optional = false)
    private Role roleid;
    @JoinColumn(name = "USERACCOUNTID", referencedColumnName = "USERACCOUNTID")
    @ManyToOne(optional = false)
    private Useraccount useraccountid;

    public Useraccountrole() {
    }

    public Useraccountrole(BigDecimal useraccountroleid) {
        this.useraccountroleid = useraccountroleid;
    }

    public BigDecimal getUseraccountroleid() {
        return useraccountroleid;
    }

    public void setUseraccountroleid(BigDecimal useraccountroleid) {
        this.useraccountroleid = useraccountroleid;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public Role getRoleid() {
        return roleid;
    }

    public void setRoleid(Role roleid) {
        this.roleid = roleid;
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
        hash += (useraccountroleid != null ? useraccountroleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useraccountrole)) {
            return false;
        }
        Useraccountrole other = (Useraccountrole) object;
        if ((this.useraccountroleid == null && other.useraccountroleid != null) || (this.useraccountroleid != null && !this.useraccountroleid.equals(other.useraccountroleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Useraccountrole[ useraccountroleid=" + useraccountroleid + " ]";
    }
    
}
