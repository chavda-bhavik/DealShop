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
@Table(name = "carttb")
@NamedQueries({
    @NamedQuery(name = "Carttb.findAll", query = "SELECT c FROM Carttb c"),
    @NamedQuery(name = "Carttb.findByCartID", query = "SELECT c FROM Carttb c WHERE c.cartID = :cartID"),
    @NamedQuery(name = "Carttb.findByAddDate", query = "SELECT c FROM Carttb c WHERE c.addDate = :addDate"),
    @NamedQuery(name = "Carttb.findByIsPaid", query = "SELECT c FROM Carttb c WHERE c.isPaid = :isPaid")})
public class Carttb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CartID")
    private Integer cartID;
    @Basic(optional = false)
    @Column(name = "AddDate")
    @Temporal(TemporalType.DATE)
    private Date addDate;
    @Basic(optional = false)
    @Column(name = "isPaid")
    private int isPaid;
    @JoinColumn(name = "DealID", referencedColumnName = "DealID")
    @ManyToOne(optional = false)
    private Dealstb dealID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Carttb() {
    }

    public Carttb(Integer cartID) {
        this.cartID = cartID;
    }

    public Carttb(Integer cartID, Date addDate, int isPaid) {
        this.cartID = cartID;
        this.addDate = addDate;
        this.isPaid = isPaid;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartID != null ? cartID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carttb)) {
            return false;
        }
        Carttb other = (Carttb) object;
        if ((this.cartID == null && other.cartID != null) || (this.cartID != null && !this.cartID.equals(other.cartID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Carttb[ cartID=" + cartID + " ]";
    }
    
}
