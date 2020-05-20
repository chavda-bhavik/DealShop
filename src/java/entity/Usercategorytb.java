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
@Table(name = "usercategorytb")
@NamedQueries({
    @NamedQuery(name = "Usercategorytb.findAll", query = "SELECT u FROM Usercategorytb u"),
    @NamedQuery(name = "Usercategorytb.findByCategoryID", query = "SELECT u FROM Usercategorytb u WHERE u.categoryID = :categoryID"),
    @NamedQuery(name = "Usercategorytb.findByName", query = "SELECT u FROM Usercategorytb u WHERE u.name = :name")})
public class Usercategorytb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CategoryID")
    private Integer categoryID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCategoryID")
    private Collection<Usertb> usertbCollection;

    public Usercategorytb() {
    }

    public Usercategorytb(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Usercategorytb(Integer categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
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

    @JsonbTransient
    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
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
        if (!(object instanceof Usercategorytb)) {
            return false;
        }
        Usercategorytb other = (Usercategorytb) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usercategorytb[ categoryID=" + categoryID + " ]";
    }
    
}
