/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import entity.AdminDashboard;
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
@Named(value = "adminHomeBean")
@RequestScoped
public class AdminHomeBean {

    AdminClient admin;
    Response res;
    
    AdminDashboard dashboard;
    GenericType<AdminDashboard> gDashboard;

    public AdminDashboard getDashboard() {
        return dashboard;
    }
    
    public void getDashboardData() {
        res = admin.getAdminDashboard(Response.class);
        dashboard = res.readEntity(gDashboard);
    }
    
    public AdminHomeBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        admin = new AdminClient(token);
        gDashboard = new GenericType<AdminDashboard>(){};
    }
    
}
