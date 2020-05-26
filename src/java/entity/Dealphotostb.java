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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "dealphotostb")
@NamedQueries({
    @NamedQuery(name = "Dealphotostb.findAll", query = "SELECT d FROM Dealphotostb d"),
    @NamedQuery(name = "Dealphotostb.findByPhotoID", query = "SELECT d FROM Dealphotostb d WHERE d.photoID = :photoID"),
    @NamedQuery(name = "Dealphotostb.findByPhoto", query = "SELECT d FROM Dealphotostb d WHERE d.photo = :photo")})
public class Dealphotostb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PhotoID")
    private Integer photoID;
    @Basic(optional = false)
    @Column(name = "Photo")
    private String photo;
    @JoinColumn(name = "DealID", referencedColumnName = "DealID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dealstb dealID;

    public Dealphotostb() {
    }

    public Dealphotostb(Integer photoID) {
        this.photoID = photoID;
    }

    public Dealphotostb(Integer photoID, String photo) {
        this.photoID = photoID;
        this.photo = photo;
    }

    public Integer getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Integer photoID) {
        this.photoID = photoID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        hash += (photoID != null ? photoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealphotostb)) {
            return false;
        }
        Dealphotostb other = (Dealphotostb) object;
        if ((this.photoID == null && other.photoID != null) || (this.photoID != null && !this.photoID.equals(other.photoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealphotostb[ photoID=" + photoID + " ]";
    }
    
}
