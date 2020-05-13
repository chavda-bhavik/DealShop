/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "businesstypetb")
@NamedQueries({
    @NamedQuery(name = "Businesstypetb.findAll", query = "SELECT b FROM Businesstypetb b"),
    @NamedQuery(name = "Businesstypetb.findByBusinessTypeID", query = "SELECT b FROM Businesstypetb b WHERE b.businessTypeID = :businessTypeID"),
    @NamedQuery(name = "Businesstypetb.findByName", query = "SELECT b FROM Businesstypetb b WHERE b.name = :name"),
    @NamedQuery(name = "Businesstypetb.findByImage", query = "SELECT b FROM Businesstypetb b WHERE b.image = :image")})
public class Businesstypetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusinessTypeID")
    private Integer businessTypeID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Image")
    private String image;
    @OneToMany(mappedBy = "businessTypeID", fetch = FetchType.EAGER)
    private Collection<Businesstb> businesstbCollection;
    @JoinColumn(name = "BusinessCategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Businesscategorytb businessCategoryID;

    public Businesstypetb() {
    }

    public Businesstypetb(Integer businessTypeID) {
        this.businessTypeID = businessTypeID;
    }

    public Businesstypetb(Integer businessTypeID, String name, String image) {
        this.businessTypeID = businessTypeID;
        this.name = name;
        this.image = image;
    }

    public Integer getBusinessTypeID() {
        return businessTypeID;
    }

    public void setBusinessTypeID(Integer businessTypeID) {
        this.businessTypeID = businessTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonbTransient
    public Collection<Businesstb> getBusinesstbCollection() {
        return businesstbCollection;
    }

    public void setBusinesstbCollection(Collection<Businesstb> businesstbCollection) {
        this.businesstbCollection = businesstbCollection;
    }

    public Businesscategorytb getBusinessCategoryID() {
        return businessCategoryID;
    }

    public void setBusinessCategoryID(Businesscategorytb businessCategoryID) {
        this.businessCategoryID = businessCategoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessTypeID != null ? businessTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesstypetb)) {
            return false;
        }
        Businesstypetb other = (Businesstypetb) object;
        if ((this.businessTypeID == null && other.businessTypeID != null) || (this.businessTypeID != null && !this.businessTypeID.equals(other.businessTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businesstypetb[ businessTypeID=" + businessTypeID + " ]";
    }
    
}
