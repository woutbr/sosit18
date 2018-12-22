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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "COMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findByCompanyid", query = "SELECT c FROM Company c WHERE c.companyid = :companyid")
    , @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
    , @NamedQuery(name = "Company.findByStreet", query = "SELECT c FROM Company c WHERE c.street = :street")
    , @NamedQuery(name = "Company.findByHousenumber", query = "SELECT c FROM Company c WHERE c.housenumber = :housenumber")
    , @NamedQuery(name = "Company.findByPostalcode", query = "SELECT c FROM Company c WHERE c.postalcode = :postalcode")
    , @NamedQuery(name = "Company.findByPlace", query = "SELECT c FROM Company c WHERE c.place = :place")
    , @NamedQuery(name = "Company.findByVat", query = "SELECT c FROM Company c WHERE c.vat = :vat")
    , @NamedQuery(name = "Company.findByVersion", query = "SELECT c FROM Company c WHERE c.version = :version")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id      
    @SequenceGenerator(name="COMPANY_SEQ",sequenceName="COMPANY_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "COMPANY_SEQ")
    
    @Column(name = "COMPANYID")
    private BigDecimal companyid;
    @Size(max = 250)
    @Column(name = "NAME")
    private String name;
    @Size(max = 250)
    @Column(name = "STREET")
    private String street;
    @Size(max = 10)
    @Column(name = "HOUSENUMBER")
    private String housenumber;
    @Size(max = 50)
    @Column(name = "POSTALCODE")
    private String postalcode;
    @Size(max = 200)
    @Column(name = "PLACE")
    private String place;
    @Size(max = 100)
    @Column(name = "VAT")
    private String vat;
    @Column(name = "VERSION")
    private BigInteger version;
    @OneToMany(mappedBy = "companyid")
    private Collection<Asset> assetCollection;
    @OneToMany(mappedBy = "companyid")
    private Collection<Useraccount> useraccountCollection;

    public Company() {
    }

    public Company(BigDecimal companyid) {
        this.companyid = companyid;
    }

    public Company(BigDecimal companyid, String name) {
        this.companyid = companyid;
        this.name = name;
    }

    public BigDecimal getCompanyid() {
        return companyid;
    }

    public void setCompanyid(BigDecimal companyid) {
        this.companyid = companyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
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

    @XmlTransient
    public Collection<Useraccount> getUseraccountCollection() {
        return useraccountCollection;
    }

    public void setUseraccountCollection(Collection<Useraccount> useraccountCollection) {
        this.useraccountCollection = useraccountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyid != null ? companyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyid == null && other.companyid != null) || (this.companyid != null && !this.companyid.equals(other.companyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Company[ companyid=" + companyid + " ]";
    }

    
    
}
