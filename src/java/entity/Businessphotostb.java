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
@Table(name = "businessphotostb")
@NamedQueries({
    @NamedQuery(name = "Businessphotostb.findAll", query = "SELECT b FROM Businessphotostb b"),
    @NamedQuery(name = "Businessphotostb.findByPhotoID", query = "SELECT b FROM Businessphotostb b WHERE b.photoID = :photoID"),
    @NamedQuery(name = "Businessphotostb.findByPhoto", query = "SELECT b FROM Businessphotostb b WHERE b.photo = :photo"),
    @NamedQuery(name = "Businessphotostb.findByType", query = "SELECT b FROM Businessphotostb b WHERE b.type = :type")})
public class Businessphotostb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PhotoID")
    private Integer photoID;
    @Basic(optional = false)
    @Column(name = "Photo")
    private String photo;
    @Basic(optional = false)
    @Column(name = "Type")
    private int type;
    @JoinColumn(name = "BusinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Businesstb businessID;

    public Businessphotostb() {
    }

    public Businessphotostb(Integer photoID) {
        this.photoID = photoID;
    }

    public Businessphotostb(Integer photoID, String photo, int type) {
        this.photoID = photoID;
        this.photo = photo;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        hash += (photoID != null ? photoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businessphotostb)) {
            return false;
        }
        Businessphotostb other = (Businessphotostb) object;
        if ((this.photoID == null && other.photoID != null) || (this.photoID != null && !this.photoID.equals(other.photoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businessphotostb[ photoID=" + photoID + " ]";
    }
    
}
