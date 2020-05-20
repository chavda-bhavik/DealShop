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
@Table(name = "statetb")
@NamedQueries({
    @NamedQuery(name = "Statetb.findAll", query = "SELECT s FROM Statetb s"),
    @NamedQuery(name = "Statetb.findByStateID", query = "SELECT s FROM Statetb s WHERE s.stateID = :stateID"),
    @NamedQuery(name = "Statetb.findByName", query = "SELECT s FROM Statetb s WHERE s.name = :name")})
public class Statetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "StateID")
    private Integer stateID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateID")
    private Collection<Citytb> citytbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateID")
    private Collection<Businesstb> businesstbCollection;

    public Statetb() {
    }

    public Statetb(Integer stateID) {
        this.stateID = stateID;
    }

    public Statetb(Integer stateID, String name) {
        this.stateID = stateID;
        this.name = name;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonbTransient
    public Collection<Citytb> getCitytbCollection() {
        return citytbCollection;
    }

    public void setCitytbCollection(Collection<Citytb> citytbCollection) {
        this.citytbCollection = citytbCollection;
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
        hash += (stateID != null ? stateID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statetb)) {
            return false;
        }
        Statetb other = (Statetb) object;
        if ((this.stateID == null && other.stateID != null) || (this.stateID != null && !this.stateID.equals(other.stateID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Statetb[ stateID=" + stateID + " ]";
    }
    
}
