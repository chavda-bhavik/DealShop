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
@Table(name = "informationtb")
@NamedQueries({
    @NamedQuery(name = "Informationtb.findAll", query = "SELECT i FROM Informationtb i"),
    @NamedQuery(name = "Informationtb.findByInformationID", query = "SELECT i FROM Informationtb i WHERE i.informationID = :informationID"),
    @NamedQuery(name = "Informationtb.findByTitle", query = "SELECT i FROM Informationtb i WHERE i.title = :title")})
public class Informationtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InformationID")
    private Integer informationID;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informationID")
    private Collection<Businessinfotb> businessinfotbCollection;

    public Informationtb() {
    }

    public Informationtb(Integer informationID) {
        this.informationID = informationID;
    }

    public Informationtb(Integer informationID, String title) {
        this.informationID = informationID;
        this.title = title;
    }

    public Integer getInformationID() {
        return informationID;
    }

    public void setInformationID(Integer informationID) {
        this.informationID = informationID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonbTransient
    public Collection<Businessinfotb> getBusinessinfotbCollection() {
        return businessinfotbCollection;
    }

    public void setBusinessinfotbCollection(Collection<Businessinfotb> businessinfotbCollection) {
        this.businessinfotbCollection = businessinfotbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informationID != null ? informationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informationtb)) {
            return false;
        }
        Informationtb other = (Informationtb) object;
        if ((this.informationID == null && other.informationID != null) || (this.informationID != null && !this.informationID.equals(other.informationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Informationtb[ informationID=" + informationID + " ]";
    }
    
}
