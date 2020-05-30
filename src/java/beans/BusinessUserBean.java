/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Businesstb;
import entity.Reviewtb;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "businessUserBean")
@RequestScoped
public class BusinessUserBean {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    CommonClient common;
    Response res;
    GenericType<Collection<Businesstb>> gBusinesses;
    Collection<Businesstb> businesses;
    GenericType<Collection<Reviewtb>> gReviews;
    Collection<Reviewtb> reviews;
    GenericType<Businesstb> gBusiness;
    Businesstb business;

    public void checkStoreAndRedirect() throws IOException {
        if(business==null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/user2/Storelist.jsf");
        }
    }
    public Collection<Reviewtb> getReviews() {
        return reviews;
    }
    
    public Businesstb getBusiness() {
        return business;
    }

    public Collection<Businesstb> getBusinesses() {
        Object city = session.getAttribute("cityId");
        String cityId = "3";
        if(city != null) {
            cityId = city.toString();
        }
        res = common.getBusinessesUser(Response.class, cityId);
        businesses = res.readEntity(gBusinesses);
        return businesses;
    }
    
    public String getSingleBusiness(int BusinessId) {
        res = common.getBusiness(Response.class, String.valueOf(BusinessId));
        business = res.readEntity(gBusiness);
        res = common.getBusinessReviews(Response.class, String.valueOf(BusinessId));
        reviews = res.readEntity(gReviews);
        return "/user2/Store.jsf";
    }
    
    
    public BusinessUserBean() {
        common = new CommonClient();
        gBusinesses = new GenericType<Collection<Businesstb>>(){};
        gReviews = new GenericType<Collection<Reviewtb>>(){};
        gBusiness = new GenericType<Businesstb>(){};
    }
    
}
