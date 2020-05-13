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
@Table(name = "linkstb")
@NamedQueries({
    @NamedQuery(name = "Linkstb.findAll", query = "SELECT l FROM Linkstb l"),
    @NamedQuery(name = "Linkstb.findByLinkID", query = "SELECT l FROM Linkstb l WHERE l.linkID = :linkID"),
    @NamedQuery(name = "Linkstb.findByName", query = "SELECT l FROM Linkstb l WHERE l.name = :name"),
    @NamedQuery(name = "Linkstb.findByLogo", query = "SELECT l FROM Linkstb l WHERE l.logo = :logo")})
public class Linkstb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LinkID")
    private Integer linkID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linkID", fetch = FetchType.EAGER)
    private Collection<Businesslinkstb> businesslinkstbCollection;

    public Linkstb() {
    }

    public Linkstb(Integer linkID) {
        this.linkID = linkID;
    }

    public Linkstb(Integer linkID, String name) {
        this.linkID = linkID;
        this.name = name;
    }

    public Integer getLinkID() {
        return linkID;
    }

    public void setLinkID(Integer linkID) {
        this.linkID = linkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @JsonbTransient
    public Collection<Businesslinkstb> getBusinesslinkstbCollection() {
        return businesslinkstbCollection;
    }

    public void setBusinesslinkstbCollection(Collection<Businesslinkstb> businesslinkstbCollection) {
        this.businesslinkstbCollection = businesslinkstbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linkID != null ? linkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linkstb)) {
            return false;
        }
        Linkstb other = (Linkstb) object;
        if ((this.linkID == null && other.linkID != null) || (this.linkID != null && !this.linkID.equals(other.linkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Linkstb[ linkID=" + linkID + " ]";
    }
    
}
