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
    , @NamedQuery(name = "Useraccountrole.findByUseraccountid", query = "SELECT u FROM Useraccountrole u WHERE u.useraccountid = :useraccountid")
    , @NamedQuery(name = "Useraccountrole.findByRoleid", query = "SELECT u FROM Useraccountrole u WHERE u.roleid = :roleid")
    , @NamedQuery(name = "Useraccountrole.findByVersion", query = "SELECT u FROM Useraccountrole u WHERE u.version = :version")})
public class Useraccountrole implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERACCOUNTROLEID")
    private BigDecimal useraccountroleid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERACCOUNTID")
    private BigInteger useraccountid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLEID")
    private BigInteger roleid;
    @Column(name = "VERSION")
    private BigInteger version;

    public Useraccountrole() {
    }

    public Useraccountrole(BigDecimal useraccountroleid) {
        this.useraccountroleid = useraccountroleid;
    }

    public Useraccountrole(BigDecimal useraccountroleid, BigInteger useraccountid, BigInteger roleid) {
        this.useraccountroleid = useraccountroleid;
        this.useraccountid = useraccountid;
        this.roleid = roleid;
    }

    public BigDecimal getUseraccountroleid() {
        return useraccountroleid;
    }

    public void setUseraccountroleid(BigDecimal useraccountroleid) {
        this.useraccountroleid = useraccountroleid;
    }

    public BigInteger getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(BigInteger useraccountid) {
        this.useraccountid = useraccountid;
    }

    public BigInteger getRoleid() {
        return roleid;
    }

    public void setRoleid(BigInteger roleid) {
        this.roleid = roleid;
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
