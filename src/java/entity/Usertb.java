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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "usertb")
@NamedQueries({
    @NamedQuery(name = "Usertb.findAll", query = "SELECT u FROM Usertb u"),
    @NamedQuery(name = "Usertb.findByUserID", query = "SELECT u FROM Usertb u WHERE u.userID = :userID"),
    @NamedQuery(name = "Usertb.findByName", query = "SELECT u FROM Usertb u WHERE u.name = :name"),
    @NamedQuery(name = "Usertb.findByEmail", query = "SELECT u FROM Usertb u WHERE u.email = :email")})
public class Usertb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "Password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Reviewtb> reviewtbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Carttb> carttbCollection;
    @JoinColumn(name = "UserCategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Usercategorytb userCategoryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Dealspaymenttb> dealspaymenttbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Businesstb> businesstbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Dealsusagetb> dealsusagetbCollection;

    public Usertb() {
    }

    public Usertb(Integer userID) {
        this.userID = userID;
    }

    public Usertb(Integer userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonbTransient
    public Collection<Reviewtb> getReviewtbCollection() {
        return reviewtbCollection;
    }

    public void setReviewtbCollection(Collection<Reviewtb> reviewtbCollection) {
        this.reviewtbCollection = reviewtbCollection;
    }

    @JsonbTransient
    public Collection<Carttb> getCarttbCollection() {
        return carttbCollection;
    }

    public void setCarttbCollection(Collection<Carttb> carttbCollection) {
        this.carttbCollection = carttbCollection;
    }

    public Usercategorytb getUserCategoryID() {
        return userCategoryID;
    }

    public void setUserCategoryID(Usercategorytb userCategoryID) {
        this.userCategoryID = userCategoryID;
    }

    @JsonbTransient
    public Collection<Dealspaymenttb> getDealspaymenttbCollection() {
        return dealspaymenttbCollection;
    }

    public void setDealspaymenttbCollection(Collection<Dealspaymenttb> dealspaymenttbCollection) {
        this.dealspaymenttbCollection = dealspaymenttbCollection;
    }

    @JsonbTransient
    public Collection<Businesstb> getBusinesstbCollection() {
        return businesstbCollection;
    }

    public void setBusinesstbCollection(Collection<Businesstb> businesstbCollection) {
        this.businesstbCollection = businesstbCollection;
    }

    @JsonbTransient
    public Collection<Dealsusagetb> getDealsusagetbCollection() {
        return dealsusagetbCollection;
    }

    public void setDealsusagetbCollection(Collection<Dealsusagetb> dealsusagetbCollection) {
        this.dealsusagetbCollection = dealsusagetbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertb)) {
            return false;
        }
        Usertb other = (Usertb) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usertb[ userID=" + userID + " ]";
    }
    
}
