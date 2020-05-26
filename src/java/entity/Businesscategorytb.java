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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "businesscategorytb")
@NamedQueries({
    @NamedQuery(name = "Businesscategorytb.findAll", query = "SELECT b FROM Businesscategorytb b"),
    @NamedQuery(name = "Businesscategorytb.findByCategoryID", query = "SELECT b FROM Businesscategorytb b WHERE b.categoryID = :categoryID"),
    @NamedQuery(name = "Businesscategorytb.findByName", query = "SELECT b FROM Businesscategorytb b WHERE b.name = :name"),
    @NamedQuery(name = "Businesscategorytb.findByImage", query = "SELECT b FROM Businesscategorytb b WHERE b.image = :image")})
public class Businesscategorytb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CategoryID")
    private Integer categoryID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessCategoryID", fetch = FetchType.EAGER)
    private Collection<Businesstb> businesstbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessCategoryID", fetch = FetchType.EAGER)
    private Collection<Businesstypetb> businesstypetbCollection;

    public Businesscategorytb() {
    }

    public Businesscategorytb(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Businesscategorytb(Integer categoryID, String name, String image) {
        this.categoryID = categoryID;
        this.name = name;
        this.image = image;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    @JsonbTransient
    public Collection<Businesstypetb> getBusinesstypetbCollection() {
        return businesstypetbCollection;
    }

    public void setBusinesstypetbCollection(Collection<Businesstypetb> businesstypetbCollection) {
        this.businesstypetbCollection = businesstypetbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesscategorytb)) {
            return false;
        }
        Businesscategorytb other = (Businesscategorytb) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businesscategorytb[ categoryID=" + categoryID + " ]";
    }
    
}
