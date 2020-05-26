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
@Table(name = "citytb")
@NamedQueries({
    @NamedQuery(name = "Citytb.findAll", query = "SELECT c FROM Citytb c"),
    @NamedQuery(name = "Citytb.findByCityID", query = "SELECT c FROM Citytb c WHERE c.cityID = :cityID"),
    @NamedQuery(name = "Citytb.findByName", query = "SELECT c FROM Citytb c WHERE c.name = :name")})
public class Citytb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CityID")
    private Integer cityID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @JoinColumn(name = "StateID", referencedColumnName = "StateID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Statetb stateID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityID", fetch = FetchType.EAGER)
    private Collection<Businesstb> businesstbCollection;

    public Citytb() {
    }

    public Citytb(Integer cityID) {
        this.cityID = cityID;
    }

    public Citytb(Integer cityID, String name) {
        this.cityID = cityID;
        this.name = name;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statetb getStateID() {
        return stateID;
    }

    public void setStateID(Statetb stateID) {
        this.stateID = stateID;
    }

    @JsonbTransient
    public Collection<Businesstb> getBusinesstbCollection() {
        return businesstbCollection;
    }

    public void setBusinesstbCollection(Collection<Businesstb> businesstbCollection) {
        this.businesstbCollection = businesstbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityID != null ? cityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citytb)) {
            return false;
        }
        Citytb other = (Citytb) object;
        if ((this.cityID == null && other.cityID != null) || (this.cityID != null && !this.cityID.equals(other.cityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Citytb[ cityID=" + cityID + " ]";
    }
    
}
