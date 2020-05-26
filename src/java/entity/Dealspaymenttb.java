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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "dealspaymenttb")
@NamedQueries({
    @NamedQuery(name = "Dealspaymenttb.findAll", query = "SELECT d FROM Dealspaymenttb d"),
    @NamedQuery(name = "Dealspaymenttb.findByPaymentID", query = "SELECT d FROM Dealspaymenttb d WHERE d.paymentID = :paymentID"),
    @NamedQuery(name = "Dealspaymenttb.findByPaymentMode", query = "SELECT d FROM Dealspaymenttb d WHERE d.paymentMode = :paymentMode"),
    @NamedQuery(name = "Dealspaymenttb.findByPaymentDate", query = "SELECT d FROM Dealspaymenttb d WHERE d.paymentDate = :paymentDate"),
    @NamedQuery(name = "Dealspaymenttb.findByStatus", query = "SELECT d FROM Dealspaymenttb d WHERE d.status = :status"),
    @NamedQuery(name = "Dealspaymenttb.findByIsEntered", query = "SELECT d FROM Dealspaymenttb d WHERE d.isEntered = :isEntered")})
public class Dealspaymenttb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PaymentID")
    private Integer paymentID;
    @Basic(optional = false)
    @Column(name = "PaymentMode")
    private int paymentMode;
    @Basic(optional = false)
    @Column(name = "PaymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Basic(optional = false)
    @Column(name = "Status")
    private int status;
    @Basic(optional = false)
    @Column(name = "isEntered")
    private int isEntered;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usertb userID;
    @JoinColumn(name = "OfferID", referencedColumnName = "OfferID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Offertb offerID;

    public Dealspaymenttb() {
    }

    public Dealspaymenttb(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public Dealspaymenttb(Integer paymentID, int paymentMode, Date paymentDate, int status, int isEntered) {
        this.paymentID = paymentID;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.status = status;
        this.isEntered = isEntered;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsEntered() {
        return isEntered;
    }

    public void setIsEntered(int isEntered) {
        this.isEntered = isEntered;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    public Offertb getOfferID() {
        return offerID;
    }

    public void setOfferID(Offertb offerID) {
        this.offerID = offerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentID != null ? paymentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealspaymenttb)) {
            return false;
        }
        Dealspaymenttb other = (Dealspaymenttb) object;
        if ((this.paymentID == null && other.paymentID != null) || (this.paymentID != null && !this.paymentID.equals(other.paymentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealspaymenttb[ paymentID=" + paymentID + " ]";
    }
    
}
