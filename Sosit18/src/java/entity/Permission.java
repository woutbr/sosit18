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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PERMISSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
    , @NamedQuery(name = "Permission.findByPermissionid", query = "SELECT p FROM Permission p WHERE p.permissionid = :permissionid")
    , @NamedQuery(name = "Permission.findByCanedit", query = "SELECT p FROM Permission p WHERE p.canedit = :canedit")
    , @NamedQuery(name = "Permission.findByCanread", query = "SELECT p FROM Permission p WHERE p.canread = :canread")
    , @NamedQuery(name = "Permission.findByCaninsert", query = "SELECT p FROM Permission p WHERE p.caninsert = :caninsert")
    , @NamedQuery(name = "Permission.findByCandelete", query = "SELECT p FROM Permission p WHERE p.candelete = :candelete")
    , @NamedQuery(name = "Permission.findByVersion", query = "SELECT p FROM Permission p WHERE p.version = :version")})
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERMISSIONID")
    private BigDecimal permissionid;
    @Column(name = "CANEDIT")
    private Short canedit;
    @Column(name = "CANREAD")
    private Short canread;
    @Column(name = "CANINSERT")
    private Short caninsert;
    @Column(name = "CANDELETE")
    private Short candelete;
    @Column(name = "VERSION")
    private BigInteger version;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne(optional = false)
    private Role roleid;

    public Permission() {
    }

    public Permission(BigDecimal permissionid) {
        this.permissionid = permissionid;
    }

    public BigDecimal getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(BigDecimal permissionid) {
        this.permissionid = permissionid;
    }

    public Short getCanedit() {
        return canedit;
    }

    public void setCanedit(Short canedit) {
        this.canedit = canedit;
    }

    public Short getCanread() {
        return canread;
    }

    public void setCanread(Short canread) {
        this.canread = canread;
    }

    public Short getCaninsert() {
        return caninsert;
    }

    public void setCaninsert(Short caninsert) {
        this.caninsert = caninsert;
    }

    public Short getCandelete() {
        return candelete;
    }

    public void setCandelete(Short candelete) {
        this.candelete = candelete;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionid != null ? permissionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.permissionid == null && other.permissionid != null) || (this.permissionid != null && !this.permissionid.equals(other.permissionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Permission[ permissionid=" + permissionid + " ]";
    }
    
}
