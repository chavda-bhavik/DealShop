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
import javax.persistence.FetchType;
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
@Table(name = "reviewtb")
@NamedQueries({
    @NamedQuery(name = "Reviewtb.findAll", query = "SELECT r FROM Reviewtb r"),
    @NamedQuery(name = "Reviewtb.findByReviewID", query = "SELECT r FROM Reviewtb r WHERE r.reviewID = :reviewID"),
    @NamedQuery(name = "Reviewtb.findByRate", query = "SELECT r FROM Reviewtb r WHERE r.rate = :rate"),
    @NamedQuery(name = "Reviewtb.findByTitle", query = "SELECT r FROM Reviewtb r WHERE r.title = :title")})
public class Reviewtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReviewID")
    private Integer reviewID;
    @Basic(optional = false)
    @Column(name = "Rate")
    private int rate;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "Review")
    private String review;
    @JoinColumn(name = "BussinessID", referencedColumnName = "BusinessID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Businesstb bussinessID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usertb userID;

    public Reviewtb() {
    }

    public Reviewtb(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Reviewtb(Integer reviewID, int rate, String title, String review) {
        this.reviewID = reviewID;
        this.rate = rate;
        this.title = title;
        this.review = review;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Businesstb getBussinessID() {
        return bussinessID;
    }

    public void setBussinessID(Businesstb bussinessID) {
        this.bussinessID = bussinessID;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reviewtb)) {
            return false;
        }
        Reviewtb other = (Reviewtb) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reviewtb[ reviewID=" + reviewID + " ]";
    }
    
}
