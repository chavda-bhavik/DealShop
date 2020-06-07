/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import entity.Redeems;
import entity.Redeemtb;
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
@Named(value = "adminRedeemBean")
@RequestScoped
public class AdminRedeemBean {

    private AdminClient adminClient;
    private Response res;
    GenericType<Collection<Redeems>> gRedeems;
    private Collection<Redeems> redeems;
    GenericType<Collection<Redeemtb>> gGRedeems;
    private Collection<Redeemtb> GivenRedeems;

    public Collection<Redeemtb> getGivenRedeems() {
        res = adminClient.getGivenRedeems(Response.class);
        GivenRedeems = res.readEntity(gGRedeems);
        return GivenRedeems;
    }

    public Collection<Redeems> getRedeems() {
        res = adminClient.getPendingRedeems(Response.class);
        redeems = res.readEntity(gRedeems);
        return redeems;
    }
    
    public void redeem(int BusinessID, int amount) {
        adminClient.setBusinessRedeems(String.valueOf(BusinessID), String.valueOf(amount));
    }
    
    public AdminRedeemBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        gRedeems  = new GenericType<Collection<Redeems>>(){};
        gGRedeems = new GenericType<Collection<Redeemtb>>(){};
    }
}
