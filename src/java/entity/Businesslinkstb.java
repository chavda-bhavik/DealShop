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
@Table(name = "businesslinkstb")
@NamedQueries({
    @NamedQuery(name = "Businesslinkstb.findAll", query = "SELECT b FROM Businesslinkstb b"),
    @NamedQuery(name = "Businesslinkstb.findByBusinessLinkID", query = "SELECT b FROM Businesslinkstb b WHERE b.businessLinkID = :businessLinkID"),
    @NamedQuery(name = "Businesslinkstb.findByLink", query = "SELECT b FROM Businesslinkstb b WHERE b.link = :link")})
public class Businesslinkstb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusinessLinkID")
    private Integer businessLinkID;
    @Basic(optional = false)
    @Column(name = "Link")
    private String link;
    @JoinColumn(name = "LinkID", referencedColumnName = "LinkID")
    @ManyToOne(optional = false)
    private Linkstb linkID;
    @JoinColumn(name = "BussinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false)
    private Businesstb bussinessID;

    public Businesslinkstb() {
    }

    public Businesslinkstb(Integer businessLinkID) {
        this.businessLinkID = businessLinkID;
    }

    public Businesslinkstb(Integer businessLinkID, String link) {
        this.businessLinkID = businessLinkID;
        this.link = link;
    }

    public Integer getBusinessLinkID() {
        return businessLinkID;
    }

    public void setBusinessLinkID(Integer businessLinkID) {
        this.businessLinkID = businessLinkID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Linkstb getLinkID() {
        return linkID;
    }

    public void setLinkID(Linkstb linkID) {
        this.linkID = linkID;
    }

    public Businesstb getBussinessID() {
        return bussinessID;
    }

    public void setBussinessID(Businesstb bussinessID) {
        this.bussinessID = bussinessID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessLinkID != null ? businessLinkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesslinkstb)) {
            return false;
        }
        Businesslinkstb other = (Businesslinkstb) object;
        if ((this.businessLinkID == null && other.businessLinkID != null) || (this.businessLinkID != null && !this.businessLinkID.equals(other.businessLinkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businesslinkstb[ businessLinkID=" + businessLinkID + " ]";
    }
    
}
