/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Redeemtb;
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
@Named(value = "bRedeemsBean")
@RequestScoped
public class BRedeemsBean {

    CommonClient common;
    BusinessClient business;
    Response res;
    private String businessId;
    
    GenericType<Collection<Redeemtb>> gRedeems;
    Collection<Redeemtb> redeems;

    public Collection<Redeemtb> getRedeems() {
        res = business.getBusinessRedeems(Response.class, businessId);
        redeems = res.readEntity(gRedeems);
        return redeems;
    }
    
    public BRedeemsBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        businessId = request.getSession().getAttribute("businessid").toString();
        String token = request.getSession().getAttribute("token").toString();
        business = new BusinessClient(token);
        gRedeems = new GenericType<Collection<Redeemtb>>(){};
    }
    
}
