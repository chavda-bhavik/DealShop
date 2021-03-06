/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Businesstb;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "adminBuBean")
@SessionScoped
public class AdminBuBean implements Serializable{
    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    GenericType<Collection<Businesstb>> gBusinesses;
    Collection<Businesstb> businesses;
    
    public Collection<Businesstb> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Collection<Businesstb> businesses) {
        this.businesses = businesses;
    }
    
    public void getAllBusinesses() {
        res = commonClient.getAllBusiness(Response.class);
        businesses = res.readEntity(gBusinesses);
    }
    
    public void verifySuccessBusiness(int BusinessId) {
        adminClient.verifyBusinessSuccess(String.valueOf(BusinessId));
    }
    
    public String viewBusiness(int BusinessId) {
        System.out.println("Show business for "+BusinessId);
        return "/admin/businessView.jsf?faces-redirect=true";
    }

    public AdminBuBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        
        gBusinesses = new GenericType<Collection<Businesstb>>(){};
    }
    
}
