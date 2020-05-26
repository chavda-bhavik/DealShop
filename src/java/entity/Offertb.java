/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "offertb")
@NamedQueries({
    @NamedQuery(name = "Offertb.findAll", query = "SELECT o FROM Offertb o"),
    @NamedQuery(name = "Offertb.findByOfferID", query = "SELECT o FROM Offertb o WHERE o.offerID = :offerID"),
    @NamedQuery(name = "Offertb.findByName", query = "SELECT o FROM Offertb o WHERE o.name = :name"),
    @NamedQuery(name = "Offertb.findByCode", query = "SELECT o FROM Offertb o WHERE o.code = :code"),
    @NamedQuery(name = "Offertb.findByIssueDate", query = "SELECT o FROM Offertb o WHERE o.issueDate = :issueDate"),
    @NamedQuery(name = "Offertb.findByDueDate", query = "SELECT o FROM Offertb o WHERE o.dueDate = :dueDate"),
    @NamedQuery(name = "Offertb.findByBannerImage", query = "SELECT o FROM Offertb o WHERE o.bannerImage = :bannerImage"),
    @NamedQuery(name = "Offertb.findByOfferImage", query = "SELECT o FROM Offertb o WHERE o.offerImage = :offerImage"),
    @NamedQuery(name = "Offertb.findByPercentOff", query = "SELECT o FROM Offertb o WHERE o.percentOff = :percentOff"),
    @NamedQuery(name = "Offertb.findByDollarsOff", query = "SELECT o FROM Offertb o WHERE o.dollarsOff = :dollarsOff")})
public class Offertb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OfferID")
    private Integer offerID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Lob
    @Column(name = "TermsConditions")
    private String termsConditions;
    @Basic(optional = false)
    @Column(name = "IssueDate")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Basic(optional = false)
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    @Column(name = "BannerImage")
    private String bannerImage;
    @Basic(optional = false)
    @Column(name = "OfferImage")
    private String offerImage;
    @Basic(optional = false)
    @Column(name = "PercentOff")
    private int percentOff;
    @Basic(optional = false)
    @Column(name = "DollarsOff")
    private int dollarsOff;
    @OneToMany(mappedBy = "offerID", fetch = FetchType.EAGER)
    private Collection<Dealspaymenttb> dealspaymenttbCollection;

    public Offertb() {
    }

    public Offertb(Integer offerID) {
        this.offerID = offerID;
    }

    public Offertb(Integer offerID, String name, String code, String termsConditions, Date issueDate, Date dueDate, String bannerImage, String offerImage, int percentOff, int dollarsOff) {
        this.offerID = offerID;
        this.name = name;
        this.code = code;
        this.termsConditions = termsConditions;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.bannerImage = bannerImage;
        this.offerImage = offerImage;
        this.percentOff = percentOff;
        this.dollarsOff = dollarsOff;
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public int getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(int percentOff) {
        this.percentOff = percentOff;
    }

    public int getDollarsOff() {
        return dollarsOff;
    }

    public void setDollarsOff(int dollarsOff) {
        this.dollarsOff = dollarsOff;
    }

    @JsonbTransient
    public Collection<Dealspaymenttb> getDealspaymenttbCollection() {
        return dealspaymenttbCollection;
    }

    public void setDealspaymenttbCollection(Collection<Dealspaymenttb> dealspaymenttbCollection) {
        this.dealspaymenttbCollection = dealspaymenttbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (offerID != null ? offerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offertb)) {
            return false;
        }
        Offertb other = (Offertb) object;
        if ((this.offerID == null && other.offerID != null) || (this.offerID != null && !this.offerID.equals(other.offerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Offertb[ offerID=" + offerID + " ]";
    }
    
}
