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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "dealstb")
@NamedQueries({
    @NamedQuery(name = "Dealstb.findAll", query = "SELECT d FROM Dealstb d"),
    @NamedQuery(name = "Dealstb.findByDealID", query = "SELECT d FROM Dealstb d WHERE d.dealID = :dealID"),
    @NamedQuery(name = "Dealstb.findByName", query = "SELECT d FROM Dealstb d WHERE d.name = :name"),
    @NamedQuery(name = "Dealstb.findByIssueDate", query = "SELECT d FROM Dealstb d WHERE d.issueDate = :issueDate"),
    @NamedQuery(name = "Dealstb.findByDueDate", query = "SELECT d FROM Dealstb d WHERE d.dueDate = :dueDate"),
    @NamedQuery(name = "Dealstb.findBySoldNo", query = "SELECT d FROM Dealstb d WHERE d.soldNo = :soldNo"),
    @NamedQuery(name = "Dealstb.findByAverageCost", query = "SELECT d FROM Dealstb d WHERE d.averageCost = :averageCost"),
    @NamedQuery(name = "Dealstb.findByBannerImage", query = "SELECT d FROM Dealstb d WHERE d.bannerImage = :bannerImage"),
    @NamedQuery(name = "Dealstb.findByIsVerified", query = "SELECT d FROM Dealstb d WHERE d.isVerified = :isVerified")})
public class Dealstb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DealID")
    private Integer dealID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "IssueDate")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Basic(optional = false)
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    @Column(name = "SoldNo")
    private int soldNo;
    @Basic(optional = false)
    @Column(name = "AverageCost")
    private int averageCost;
    @Basic(optional = false)
    @Column(name = "BannerImage")
    private String bannerImage;
    @Basic(optional = false)
    @Column(name = "isVerified")
    private int isVerified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealID")
    private Collection<Dealphotostb> dealphotostbCollection;
    @JoinColumn(name = "BusinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false)
    private Businesstb businessID;
    @JoinColumn(name = "DealsCategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Dealscategorytb dealsCategoryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealID")
    private Collection<Dealsdetailstb> dealsdetailstbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealID")
    private Collection<Carttb> carttbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealID")
    private Collection<Dealsmenutb> dealsmenutbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealID")
    private Collection<Dealsusagetb> dealsusagetbCollection;

    public Dealstb() {
    }

    public Dealstb(Integer dealID) {
        this.dealID = dealID;
    }

    public Dealstb(Integer dealID, String name, Date issueDate, Date dueDate, int soldNo, int averageCost, String bannerImage, int isVerified) {
        this.dealID = dealID;
        this.name = name;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.soldNo = soldNo;
        this.averageCost = averageCost;
        this.bannerImage = bannerImage;
        this.isVerified = isVerified;
    }

    public Integer getDealID() {
        return dealID;
    }

    public void setDealID(Integer dealID) {
        this.dealID = dealID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSoldNo() {
        return soldNo;
    }

    public void setSoldNo(int soldNo) {
        this.soldNo = soldNo;
    }

    public int getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(int averageCost) {
        this.averageCost = averageCost;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    @JsonbTransient
    public Collection<Dealphotostb> getDealphotostbCollection() {
        return dealphotostbCollection;
    }

    public void setDealphotostbCollection(Collection<Dealphotostb> dealphotostbCollection) {
        this.dealphotostbCollection = dealphotostbCollection;
    }

    public Businesstb getBusinessID() {
        return businessID;
    }

    public void setBusinessID(Businesstb businessID) {
        this.businessID = businessID;
    }

    public Dealscategorytb getDealsCategoryID() {
        return dealsCategoryID;
    }

    public void setDealsCategoryID(Dealscategorytb dealsCategoryID) {
        this.dealsCategoryID = dealsCategoryID;
    }

    @JsonbTransient
    public Collection<Dealsdetailstb> getDealsdetailstbCollection() {
        return dealsdetailstbCollection;
    }

    public void setDealsdetailstbCollection(Collection<Dealsdetailstb> dealsdetailstbCollection) {
        this.dealsdetailstbCollection = dealsdetailstbCollection;
    }

    @JsonbTransient
    public Collection<Carttb> getCarttbCollection() {
        return carttbCollection;
    }

    public void setCarttbCollection(Collection<Carttb> carttbCollection) {
        this.carttbCollection = carttbCollection;
    }

    @JsonbTransient
    public Collection<Dealsmenutb> getDealsmenutbCollection() {
        return dealsmenutbCollection;
    }

    public void setDealsmenutbCollection(Collection<Dealsmenutb> dealsmenutbCollection) {
        this.dealsmenutbCollection = dealsmenutbCollection;
    }

    @JsonbTransient
    public Collection<Dealsusagetb> getDealsusagetbCollection() {
        return dealsusagetbCollection;
    }

    public void setDealsusagetbCollection(Collection<Dealsusagetb> dealsusagetbCollection) {
        this.dealsusagetbCollection = dealsusagetbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealID != null ? dealID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealstb)) {
            return false;
        }
        Dealstb other = (Dealstb) object;
        if ((this.dealID == null && other.dealID != null) || (this.dealID != null && !this.dealID.equals(other.dealID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealstb[ dealID=" + dealID + " ]";
    }
    
}
