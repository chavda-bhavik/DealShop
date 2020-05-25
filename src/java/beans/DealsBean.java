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
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "dealsBean")
@RequestScoped
public class DealsBean {
    CommonClient common;
    Response res;
    GenericType<Collection<Dealstb>> gDeals;
    private Collection<Dealstb> deals;
    private Collection<Dealstb> maxSoldDeals;
    private Collection<Dealstb> showingDeals;

    public String showDealsByCategory(int cid) {
        res = common.getDealsByCategory(Response.class, String.valueOf(cid));
        showingDeals = res.readEntity(gDeals);
        return "/user2/Deals";
    }
    
    public Collection<Dealstb> getShowingDeals() {
        return showingDeals;
    }
    public String showDealsByDueDate() {
        res = common.getDealsByDueDate(Response.class);
        showingDeals = res.readEntity(gDeals);
        return "/user/Deals";
    }
    
    public void test() {
        System.out.println("Deals Test");
    }
    
    public Collection<Dealstb> getMaxSoldDeals() {
        res = common.getDealsBySoldNo(Response.class, String.valueOf(10));
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
