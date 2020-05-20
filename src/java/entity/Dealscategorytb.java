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
@Table(name = "dealscategorytb")
@NamedQueries({
    @NamedQuery(name = "Dealscategorytb.findAll", query = "SELECT d FROM Dealscategorytb d"),
    @NamedQuery(name = "Dealscategorytb.findByCategoryID", query = "SELECT d FROM Dealscategorytb d WHERE d.categoryID = :categoryID"),
    @NamedQuery(name = "Dealscategorytb.findByName", query = "SELECT d FROM Dealscategorytb d WHERE d.name = :name"),
    @NamedQuery(name = "Dealscategorytb.findByImage", query = "SELECT d FROM Dealscategorytb d WHERE d.image = :image")})
public class Dealscategorytb implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealsCategoryID")
    private Collection<Dealstb> dealstbCollection;

    public Dealscategorytb() {
    }

    public Dealscategorytb(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Dealscategorytb(Integer categoryID, String name, String image) {
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
    public Collection<Dealstb> getDealstbCollection() {
        return dealstbCollection;
    }

    public void setDealstbCollection(Collection<Dealstb> dealstbCollection) {
        this.dealstbCollection = dealstbCollection;
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
        if (!(object instanceof Dealscategorytb)) {
            return false;
        }
        Dealscategorytb other = (Dealscategorytb) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealscategorytb[ categoryID=" + categoryID + " ]";
    }
    
}
