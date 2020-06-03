/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.UserClient;
import entity.Dealsusagetb;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "purchasedDealsBean")
@RequestScoped
public class PurchasedDealsBean {
    UserClient userClient;
    Response res;
    String userId;
    
    GenericType<Collection<Dealsusagetb>> gDUsages;
    Collection<Dealsusagetb> dusages;

    public Collection<Dealsusagetb> getDusages() {
        res = userClient.getPurchasedDeals(Response.class, userId);
        dusages = res.readEntity(gDUsages);
        return dusages;
    }
    
    public PurchasedDealsBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        userId = request.getSession().getAttribute("userid").toString();
        gDUsages = new GenericType<Collection<Dealsusagetb>>(){};
        userClient = new UserClient(token);
    }
    
}
