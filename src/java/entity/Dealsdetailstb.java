/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "dealsdetailstb")
@NamedQueries({
    @NamedQuery(name = "Dealsdetailstb.findAll", query = "SELECT d FROM Dealsdetailstb d"),
    @NamedQuery(name = "Dealsdetailstb.findByDealDetailID", query = "SELECT d FROM Dealsdetailstb d WHERE d.dealDetailID = :dealDetailID"),
    @NamedQuery(name = "Dealsdetailstb.findByCanncellationAllowed", query = "SELECT d FROM Dealsdetailstb d WHERE d.canncellationAllowed = :canncellationAllowed"),
    @NamedQuery(name = "Dealsdetailstb.findByValidFor", query = "SELECT d FROM Dealsdetailstb d WHERE d.validFor = :validFor"),
    @NamedQuery(name = "Dealsdetailstb.findByValidOn", query = "SELECT d FROM Dealsdetailstb d WHERE d.validOn = :validOn")})
public class Dealsdetailstb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DealDetailID")
    private Integer dealDetailID;
    @Basic(optional = false)
    @Column(name = "CanncellationAllowed")
    private boolean canncellationAllowed;
    @Basic(optional = false)
    @Lob
    @Column(name = "HowToUse")
    private String howToUse;
    @Basic(optional = false)
    @Lob
    @Column(name = "ThingsToRemember")
    private String thingsToRemember;
    @Basic(optional = false)
    @Lob
    @Column(name = "Inclusion")
    private String inclusion;
    @Basic(optional = false)
    @Column(name = "ValidFor")
    private int validFor;
    @Basic(optional = false)
    @Column(name = "ValidOn")
    private String validOn;
    @JoinColumn(name = "DealID", referencedColumnName = "DealID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dealstb dealID;

    public Dealsdetailstb() {
    }

    public Dealsdetailstb(Integer dealDetailID) {
        this.dealDetailID = dealDetailID;
    }

    public Dealsdetailstb(Integer dealDetailID, boolean canncellationAllowed, String howToUse, String thingsToRemember, String inclusion, int validFor, String validOn) {
        this.dealDetailID = dealDetailID;
        this.canncellationAllowed = canncellationAllowed;
        this.howToUse = howToUse;
        this.thingsToRemember = thingsToRemember;
        this.inclusion = inclusion;
        this.validFor = validFor;
        this.validOn = validOn;
    }

    public Integer getDealDetailID() {
        return dealDetailID;
    }

    public void setDealDetailID(Integer dealDetailID) {
        this.dealDetailID = dealDetailID;
    }

    public boolean getCanncellationAllowed() {
        return canncellationAllowed;
    }

    public void setCanncellationAllowed(boolean canncellationAllowed) {
        this.canncellationAllowed = canncellationAllowed;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(String howToUse) {
        this.howToUse = howToUse;
    }

    public String getThingsToRemember() {
        return thingsToRemember;
    }

    public void setThingsToRemember(String thingsToRemember) {
        this.thingsToRemember = thingsToRemember;
    }

    public String getInclusion() {
        return inclusion;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public int getValidFor() {
        return validFor;
    }

    public void setValidFor(int validFor) {
        this.validFor = validFor;
    }

    public String getValidOn() {
        return validOn;
    }

    public void setValidOn(String validOn) {
        this.validOn = validOn;
    }

    public Dealstb getDealID() {
        return dealID;
    }

    public void setDealID(Dealstb dealID) {
        this.dealID = dealID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealDetailID != null ? dealDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealsdetailstb)) {
            return false;
        }
        Dealsdetailstb other = (Dealsdetailstb) object;
        if ((this.dealDetailID == null && other.dealDetailID != null) || (this.dealDetailID != null && !this.dealDetailID.equals(other.dealDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealsdetailstb[ dealDetailID=" + dealDetailID + " ]";
    }
    
}
