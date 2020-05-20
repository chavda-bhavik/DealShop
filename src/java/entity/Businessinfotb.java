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
@Table(name = "businessinfotb")
@NamedQueries({
    @NamedQuery(name = "Businessinfotb.findAll", query = "SELECT b FROM Businessinfotb b"),
    @NamedQuery(name = "Businessinfotb.findByBusinessInfoID", query = "SELECT b FROM Businessinfotb b WHERE b.businessInfoID = :businessInfoID")})
public class Businessinfotb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusinessInfoID")
    private Integer businessInfoID;
    @JoinColumn(name = "BussinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false)
    private Businesstb bussinessID;
    @JoinColumn(name = "InformationID", referencedColumnName = "InformationID")
    @ManyToOne(optional = false)
    private Informationtb informationID;

    public Businessinfotb() {
    }

    public Businessinfotb(Integer businessInfoID) {
        this.businessInfoID = businessInfoID;
    }

    public Integer getBusinessInfoID() {
        return businessInfoID;
    }

    public void setBusinessInfoID(Integer businessInfoID) {
        this.businessInfoID = businessInfoID;
    }

    public Businesstb getBussinessID() {
        return bussinessID;
    }

    public void setBussinessID(Businesstb bussinessID) {
        this.bussinessID = bussinessID;
    }

    public Informationtb getInformationID() {
        return informationID;
    }

    public void setInformationID(Informationtb informationID) {
        this.informationID = informationID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessInfoID != null ? businessInfoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businessinfotb)) {
            return false;
        }
        Businessinfotb other = (Businessinfotb) object;
        if ((this.businessInfoID == null && other.businessInfoID != null) || (this.businessInfoID != null && !this.businessInfoID.equals(other.businessInfoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businessinfotb[ businessInfoID=" + businessInfoID + " ]";
    }
    
}
