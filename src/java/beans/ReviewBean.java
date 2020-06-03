/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.UserClient;
import entity.Businesstb;
import entity.Reviewtb;
import entity.Usertb;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bhavik
 */
@Named(value = "reviewBean")
@SessionScoped
public class ReviewBean implements Serializable {

    UserClient userClient;
    private String userId;
    
    BusinessUserBean bubean;
    
    private String title;
    private int rate;
    private String review;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
    public String giveReview() throws IOException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int businessId = Integer.valueOf(request.getParameter("businessId"));
        
        Businesstb business = new Businesstb();
        business.setBusinessID(businessId);
        Usertb user = new Usertb();
        user.setUserID(Integer.valueOf(userId));
        
        Reviewtb rvw = new Reviewtb();
        rvw.setBussinessID(business);
        rvw.setRate(rate);
        rvw.setReview(review);
        rvw.setTitle(title);
        rvw.setUserID(user);
        
        userClient.addReview(rvw);
        return "/user2/Store.jsf?store="+businessId+"&faces-redirect=true";
    }
    
    public ReviewBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        userId = request.getSession().getAttribute("userid").toString();
        
        userClient = new UserClient(token);
        review = "";
    }
    
}
