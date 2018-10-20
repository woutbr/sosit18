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
@Table(name = "ASSET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a")
    , @NamedQuery(name = "Asset.findByAssetid", query = "SELECT a FROM Asset a WHERE a.assetid = :assetid")
    , @NamedQuery(name = "Asset.findByAssetmasterid", query = "SELECT a FROM Asset a WHERE a.assetmasterid = :assetmasterid")
    , @NamedQuery(name = "Asset.findByCompanyid", query = "SELECT a FROM Asset a WHERE a.companyid = :companyid")
    , @NamedQuery(name = "Asset.findByName", query = "SELECT a FROM Asset a WHERE a.name = :name")
    , @NamedQuery(name = "Asset.findByDescription", query = "SELECT a FROM Asset a WHERE a.description = :description")
    , @NamedQuery(name = "Asset.findByAssetgroupid", query = "SELECT a FROM Asset a WHERE a.assetgroupid = :assetgroupid")
    , @NamedQuery(name = "Asset.findByAssetlocationid", query = "SELECT a FROM Asset a WHERE a.assetlocationid = :assetlocationid")
    , @NamedQuery(name = "Asset.findByPurchasedate", query = "SELECT a FROM Asset a WHERE a.purchasedate = :purchasedate")
    , @NamedQuery(name = "Asset.findByWarranty", query = "SELECT a FROM Asset a WHERE a.warranty = :warranty")
    , @NamedQuery(name = "Asset.findBySupportcontract", query = "SELECT a FROM Asset a WHERE a.supportcontract = :supportcontract")
    , @NamedQuery(name = "Asset.findByValue", query = "SELECT a FROM Asset a WHERE a.value = :value")
    , @NamedQuery(name = "Asset.findByVersion", query = "SELECT a FROM Asset a WHERE a.version = :version")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSETID")
    private BigDecimal assetid;
    @Column(name = "ASSETMASTERID")
    private BigInteger assetmasterid;
    @Column(name = "COMPANYID")
    private BigInteger companyid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NAME")
    private String name;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ASSETGROUPID")
    private BigInteger assetgroupid;
    @Column(name = "ASSETLOCATIONID")
    private BigInteger assetlocationid;
    @Column(name = "PURCHASEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchasedate;
    @Column(name = "WARRANTY")
    private BigInteger warranty;
    @Size(max = 500)
    @Column(name = "SUPPORTCONTRACT")
    private String supportcontract;
    @Column(name = "VALUE")
    private BigInteger value;
    @Column(name = "VERSION")
    private BigInteger version;

    public Asset() {
    }

    public Asset(BigDecimal assetid) {
        this.assetid = assetid;
    }

    public Asset(BigDecimal assetid, String name) {
        this.assetid = assetid;
        this.name = name;
    }

    public BigDecimal getAssetid() {
        return assetid;
    }

    public void setAssetid(BigDecimal assetid) {
        this.assetid = assetid;
    }

    public BigInteger getAssetmasterid() {
        return assetmasterid;
    }

    public void setAssetmasterid(BigInteger assetmasterid) {
        this.assetmasterid = assetmasterid;
    }

    public BigInteger getCompanyid() {
        return companyid;
    }

    public void setCompanyid(BigInteger companyid) {
        this.companyid = companyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getAssetgroupid() {
        return assetgroupid;
    }

    public void setAssetgroupid(BigInteger assetgroupid) {
        this.assetgroupid = assetgroupid;
    }

    public BigInteger getAssetlocationid() {
        return assetlocationid;
    }

    public void setAssetlocationid(BigInteger assetlocationid) {
        this.assetlocationid = assetlocationid;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public BigInteger getWarranty() {
        return warranty;
    }

    public void setWarranty(BigInteger warranty) {
        this.warranty = warranty;
    }

    public String getSupportcontract() {
        return supportcontract;
    }

    public void setSupportcontract(String supportcontract) {
        this.supportcontract = supportcontract;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
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
        hash += (assetid != null ? assetid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetid == null && other.assetid != null) || (this.assetid != null && !this.assetid.equals(other.assetid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asset[ assetid=" + assetid + " ]";
    }
    
}
