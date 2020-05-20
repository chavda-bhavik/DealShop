/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "dealsusagetb")
@NamedQueries({
    @NamedQuery(name = "Dealsusagetb.findAll", query = "SELECT d FROM Dealsusagetb d"),
    @NamedQuery(name = "Dealsusagetb.findByUsageID", query = "SELECT d FROM Dealsusagetb d WHERE d.usageID = :usageID"),
    @NamedQuery(name = "Dealsusagetb.findByStatus", query = "SELECT d FROM Dealsusagetb d WHERE d.status = :status"),
    @NamedQuery(name = "Dealsusagetb.findByUsageDate", query = "SELECT d FROM Dealsusagetb d WHERE d.usageDate = :usageDate"),
    @NamedQuery(name = "Dealsusagetb.findByUserRating", query = "SELECT d FROM Dealsusagetb d WHERE d.userRating = :userRating")})
public class Dealsusagetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UsageID")
    private Integer usageID;
    @Basic(optional = false)
    @Column(name = "Status")
    private int status;
    @Column(name = "UsageDate")
    @Temporal(TemporalType.DATE)
    private Date usageDate;
    @Column(name = "UserRating")
    private Integer userRating;
    @Lob
    @Column(name = "UserComment")
    private String userComment;
    @JoinColumn(name = "DealID", referencedColumnName = "DealID")
    @ManyToOne(optional = false)
    private Dealstb dealID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Usertb userID;
    @JoinColumn(name = "PaymentID", referencedColumnName = "PaymentID")
    @ManyToOne(optional = false)
    private Dealspaymenttb paymentID;

    public Dealsusagetb() {
    }

    public Dealsusagetb(Integer usageID) {
        this.usageID = usageID;
    }

    public Dealsusagetb(Integer usageID, int status) {
        this.usageID = usageID;
        this.status = status;
    }

    public Integer getUsageID() {
        return usageID;
    }

    public void setUsageID(Integer usageID) {
        this.usageID = usageID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Dealstb getDealID() {
        return dealID;
    }

    public void setDealID(Dealstb dealID) {
        this.dealID = dealID;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    public Dealspaymenttb getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Dealspaymenttb paymentID) {
        this.paymentID = paymentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usageID != null ? usageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealsusagetb)) {
            return false;
        }
        Dealsusagetb other = (Dealsusagetb) object;
        if ((this.usageID == null && other.usageID != null) || (this.usageID != null && !this.usageID.equals(other.usageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealsusagetb[ usageID=" + usageID + " ]";
    }
    
}
