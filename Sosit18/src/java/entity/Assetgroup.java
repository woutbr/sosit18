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
@Table(name = "ASSETGROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assetgroup.findAll", query = "SELECT a FROM Assetgroup a")
    , @NamedQuery(name = "Assetgroup.findByAssetgroupid", query = "SELECT a FROM Assetgroup a WHERE a.assetgroupid = :assetgroupid")
    , @NamedQuery(name = "Assetgroup.findByDescription", query = "SELECT a FROM Assetgroup a WHERE a.description = :description")
    , @NamedQuery(name = "Assetgroup.findBySlaid", query = "SELECT a FROM Assetgroup a WHERE a.slaid = :slaid")
    , @NamedQuery(name = "Assetgroup.findByVersion", query = "SELECT a FROM Assetgroup a WHERE a.version = :version")})
public class Assetgroup implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSETGROUPID")
    private BigDecimal assetgroupid;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SLAID")
    private BigInteger slaid;
    @Column(name = "VERSION")
    private BigInteger version;

    public Assetgroup() {
    }

    public Assetgroup(BigDecimal assetgroupid) {
        this.assetgroupid = assetgroupid;
    }

    public BigDecimal getAssetgroupid() {
        return assetgroupid;
    }

    public void setAssetgroupid(BigDecimal assetgroupid) {
        this.assetgroupid = assetgroupid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getSlaid() {
        return slaid;
    }

    public void setSlaid(BigInteger slaid) {
        this.slaid = slaid;
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
        hash += (assetgroupid != null ? assetgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assetgroup)) {
            return false;
        }
        Assetgroup other = (Assetgroup) object;
        if ((this.assetgroupid == null && other.assetgroupid != null) || (this.assetgroupid != null && !this.assetgroupid.equals(other.assetgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Assetgroup[ assetgroupid=" + assetgroupid + " ]";
    }
    
}
