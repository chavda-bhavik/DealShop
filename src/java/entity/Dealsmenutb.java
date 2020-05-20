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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bhavik
 */
@Entity
@Table(name = "dealsmenutb")
@NamedQueries({
    @NamedQuery(name = "Dealsmenutb.findAll", query = "SELECT d FROM Dealsmenutb d"),
    @NamedQuery(name = "Dealsmenutb.findByMenuID", query = "SELECT d FROM Dealsmenutb d WHERE d.menuID = :menuID")})
public class Dealsmenutb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MenuID")
    private Integer menuID;
    @Basic(optional = false)
    @Lob
    @Column(name = "MenuData")
    private String menuData;
    @JoinColumn(name = "DealID", referencedColumnName = "DealID")
    @ManyToOne(optional = false)
    private Dealstb dealID;

    public Dealsmenutb() {
    }

    public Dealsmenutb(Integer menuID) {
        this.menuID = menuID;
    }

    public Dealsmenutb(Integer menuID, String menuData) {
        this.menuID = menuID;
        this.menuData = menuData;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public String getMenuData() {
        return menuData;
    }

    public void setMenuData(String menuData) {
        this.menuData = menuData;
    }

    public Dealstb getDealID() {
        return dealID;
    }

    public void setDealID(Dealstb dealID) {
        this.dealID = dealID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuID != null ? menuID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealsmenutb)) {
            return false;
        }
        Dealsmenutb other = (Dealsmenutb) object;
        if ((this.menuID == null && other.menuID != null) || (this.menuID != null && !this.menuID.equals(other.menuID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dealsmenutb[ menuID=" + menuID + " ]";
    }
    
}
