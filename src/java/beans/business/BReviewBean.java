/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Reviewtb;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "bReviewBean")
@RequestScoped
public class BReviewBean {

    CommonClient common;
    BusinessClient business;
    Response res;
    private String businessId;
    
    GenericType<Collection<Reviewtb>> gBReviews;
    Collection<Reviewtb> breviews;

    public Collection<Reviewtb> getBreviews() {
        res = common.getBusinessReviews(Response.class, businessId);
        breviews = res.readEntity(gBReviews);
        return breviews;
    }
    
    public BReviewBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        businessId = request.getSession().getAttribute("businessid").toString();
        common = new CommonClient();
        gBReviews = new GenericType<Collection<Reviewtb>>(){};
    }
    
}
