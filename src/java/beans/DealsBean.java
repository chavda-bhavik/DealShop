/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Dealstb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "dealsBean")
@RequestScoped
public class DealsBean {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
    
    CommonClient common;
    Response res;
    GenericType<Collection<Dealstb>> gDeals;
    private Collection<Dealstb> deals;
    private Collection<Dealstb> maxSoldDeals;
    private Collection<Dealstb> showingDeals;
    private Boolean dealsFetched = false;

    
    public String showDealsByCategory(int cid) {
        //res = common.getDealsByCategory(Response.class, String.valueOf(cid));
        res = common.getDealsByCategoryUser(Response.class, String.valueOf(cid));
        showingDeals = res.readEntity(gDeals);
        dealsFetched = true;
        return "/user2/Deals";
    }
    
    public void checkDealsByBusiness() {
        String BusinessId = params.get("store");
        if(BusinessId != null) {
            res = common.getDealsByBusinessUser(Response.class, BusinessId);
            showingDeals = res.readEntity(gDeals);
            dealsFetched = true;
        }
    }
    
    public Collection<Dealstb> getShowingDeals() {
        return showingDeals;
    }
    
    public void showDealsByDueDate() {
        if(!dealsFetched) {
            res = common.getDealsByDueDate(Response.class);
            showingDeals = res.readEntity(gDeals);   
        }
    }
    
    public void test() {
        System.out.println("Deals Test");
    }
    
    public Collection<Dealstb> getMaxSoldDeals() {
        res = common.getTrandingDealsByLimitUser(Response.class, String.valueOf(5));
        maxSoldDeals = res.readEntity(gDeals);
        return maxSoldDeals;
    }

    public Collection<Dealstb> getDeals() {
        return deals;
    }

    public void setDeals(Collection<Dealstb> deals) {
        this.deals = deals;
    }
    
    public DealsBean() {
        common = new CommonClient();
        gDeals = new GenericType<Collection<Dealstb>>(){};
        deals = new ArrayList<Dealstb>();
    }
}
