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
@Table(name = "redeemtb")
@NamedQueries({
    @NamedQuery(name = "Redeemtb.findAll", query = "SELECT r FROM Redeemtb r"),
    @NamedQuery(name = "Redeemtb.findByRedeemID", query = "SELECT r FROM Redeemtb r WHERE r.redeemID = :redeemID"),
    @NamedQuery(name = "Redeemtb.findByRedeemDate", query = "SELECT r FROM Redeemtb r WHERE r.redeemDate = :redeemDate"),
    @NamedQuery(name = "Redeemtb.findByAmount", query = "SELECT r FROM Redeemtb r WHERE r.amount = :amount")})
public class Redeemtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RedeemID")
    private Integer redeemID;
    @Basic(optional = false)
    @Column(name = "RedeemDate")
    @Temporal(TemporalType.DATE)
    private Date redeemDate;
    @Basic(optional = false)
    @Column(name = "Amount")
    private int amount;
    @JoinColumn(name = "BusinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Businesstb businessID;

    public Redeemtb() {
    }

    public Redeemtb(Integer redeemID) {
        this.redeemID = redeemID;
    }

    public Redeemtb(Integer redeemID, Date redeemDate, int amount) {
        this.redeemID = redeemID;
        this.redeemDate = redeemDate;
        this.amount = amount;
    }

    public Integer getRedeemID() {
        return redeemID;
    }

    public void setRedeemID(Integer redeemID) {
        this.redeemID = redeemID;
    }

    public Date getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Date redeemDate) {
        this.redeemDate = redeemDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Businesstb getBusinessID() {
        return businessID;
    }

    public void setBusinessID(Businesstb businessID) {
        this.businessID = businessID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (redeemID != null ? redeemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Redeemtb)) {
            return false;
        }
        Redeemtb other = (Redeemtb) object;
        if ((this.redeemID == null && other.redeemID != null) || (this.redeemID != null && !this.redeemID.equals(other.redeemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Redeemtb[ redeemID=" + redeemID + " ]";
    }
    
}
