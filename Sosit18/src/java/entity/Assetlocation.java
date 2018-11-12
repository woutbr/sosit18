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
@Table(name = "ASSETLOCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assetlocation.findAll", query = "SELECT a FROM Assetlocation a")
    , @NamedQuery(name = "Assetlocation.findByAssetlocationid", query = "SELECT a FROM Assetlocation a WHERE a.assetlocationid = :assetlocationid")
    , @NamedQuery(name = "Assetlocation.findByDescription", query = "SELECT a FROM Assetlocation a WHERE a.description = :description")
    , @NamedQuery(name = "Assetlocation.findByVersion", query = "SELECT a FROM Assetlocation a WHERE a.version = :version")})
public class Assetlocation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSETLOCATIONID")
    private BigDecimal assetlocationid;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "VERSION")
    private BigInteger version;
    @OneToMany(mappedBy = "assetlocationid")
    private Collection<Asset> assetCollection;

    public Assetlocation() {
    }

    public Assetlocation(BigDecimal assetlocationid) {
        this.assetlocationid = assetlocationid;
    }

    public BigDecimal getAssetlocationid() {
        return assetlocationid;
    }

    public void setAssetlocationid(BigDecimal assetlocationid) {
        this.assetlocationid = assetlocationid;
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
    public Collection<Asset> getAssetCollection() {
        return assetCollection;
    }

    public void setAssetCollection(Collection<Asset> assetCollection) {
        this.assetCollection = assetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetlocationid != null ? assetlocationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assetlocation)) {
            return false;
        }
        Assetlocation other = (Assetlocation) object;
        if ((this.assetlocationid == null && other.assetlocationid != null) || (this.assetlocationid != null && !this.assetlocationid.equals(other.assetlocationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Assetlocation[ assetlocationid=" + assetlocationid + " ]";
    }
    
}
