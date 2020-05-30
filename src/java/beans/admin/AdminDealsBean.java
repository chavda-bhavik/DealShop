/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Dealstb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "adminDealsBean")
@SessionScoped
public class AdminDealsBean implements Serializable {
    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Dealstb>> gDeals;
    Collection<Dealstb> deals;

    public Collection<Dealstb> getDeals() {
        res = commonClient.getAllDeals(Response.class);
        deals = res.readEntity(gDeals);
        return deals;
    }
    
    public void verifyDeal(int DealID) {
        System.out.println("Deal Verified");
    }
    
    public AdminDealsBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        
        gDeals = new GenericType<Collection<Dealstb>>(){};
    }
    
}
