/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Dealstb;
import entity.Dealsusagetb;
import entity.Reviewtb;
import java.util.Collection;
import java.util.Date;
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
@Named(value = "homeBean")
@RequestScoped
public class HomeBean {

    CommonClient common;
    BusinessClient business;
    Response res;
    private String businessId;
    
    GenericType<Collection<Dealstb>> gBDeals;
    Collection<Dealstb> bdeals;
    GenericType<Collection<Reviewtb>> gBReviews;
    Collection<Reviewtb> breviews;
    GenericType<Collection<Dealsusagetb>> gDusages;
    Collection<Dealsusagetb> dusages;
    GenericType<Collection<Dealsusagetb>> gTDUsages;
    Collection<Dealsusagetb> trandingDeals;
    
    private int totalDeals;
    private int totalReviews;
    private int activeDeals;
    private int moneyEarned;
    private int dealsSold;
    private int moneyInWallet;

    public int getTotalDeals() {
        return totalDeals;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public int getActiveDeals() {
        return activeDeals;
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }

    public int getDealsSold() {
        return dealsSold;
    }

    public int getMoneyInWallet() {
        return moneyInWallet;
    }

    public Collection<Dealsusagetb> getTrandingDeals() {
        return trandingDeals;
    }
    
    
    public void getAllData() {

        // Total Deals, Active Deals
        res = business.getAllDeals(Response.class, businessId);
        bdeals = res.readEntity(gBDeals);
        
        totalDeals = bdeals.size();
        Date today = new Date();
        activeDeals = 0;
        for (Dealstb bdeal : bdeals) {
            if(bdeal.getIsVerified() == 2) {
                activeDeals += 1;
            }
        }
        
        // Sold Deals, Money Earned, Money in wallet
        res = business.getSoldDealsUsages(Response.class, businessId);
        dusages = res.readEntity(gDusages);
        dealsSold = dusages.size();
        moneyInWallet = 0;
        moneyEarned = 0;
        for (Dealsusagetb dusage : dusages) {
            moneyEarned += dusage.getDealID().getAverageCost();
            if(dusage.getStatus() == 2) {
                moneyInWallet += dusage.getDealID().getAverageCost();
            }
        }
        
        // Reviews
        res = common.getBusinessReviews(Response.class, businessId);
        breviews = res.readEntity(gBReviews);
        totalReviews = breviews.size();
        
        // Tranding Deals
        res = common.getBusinessTrandingDeals(Response.class, businessId);
        trandingDeals = res.readEntity(gTDUsages);
    }
   
    
    public HomeBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        businessId = request.getSession().getAttribute("businessid").toString();
        common = new CommonClient();
        business = new BusinessClient(token);
        gBDeals = new GenericType<Collection<Dealstb>>(){};
        gBReviews = new GenericType<Collection<Reviewtb>>(){};
        gDusages = new GenericType<Collection<Dealsusagetb>>(){};
        gTDUsages = new GenericType<Collection<Dealsusagetb>>(){};
    }
    
}
