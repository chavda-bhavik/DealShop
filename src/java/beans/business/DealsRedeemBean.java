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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "dealsRedeemBean")
@SessionScoped
public class DealsRedeemBean implements Serializable{

    CommonClient commonClient;
    BusinessClient businessClient;
    Response res;
    String BusinessId;
    
    GenericType<Collection<Dealsusagetb>> gDUsages;
    Collection<Dealsusagetb> dusages;
    GenericType<Collection<Dealsusagetb>> gSDeals;
    Collection<Dealsusagetb> sdeals;
    
    private int RedeemAmount;
    
    private String codeMessage;
    private int secretCode;
    private String code;
    private int UsageID;

    public int getRedeemAmount() {
        return RedeemAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCodeMessage() {
        return codeMessage;
    }

    public int getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(int secretCode) {
        System.out.println(secretCode);
        this.secretCode = secretCode;
    }

    public Collection<Dealsusagetb> getSdeals() {
        res = businessClient.getSoldDealsUsages(Response.class, BusinessId);
        RedeemAmount = 0;
        sdeals = new ArrayList<>();
        Collection<Dealsusagetb> usages = res.readEntity(gDUsages);
        for (Dealsusagetb usage : usages) {
            if(usage.getStatus() == 2) {
                sdeals.add(usage);
                //RedeemAmount += usage.getDealID().getAverageCost();
            }
        }
        return sdeals;
    }

    public Collection<Dealsusagetb> getDusages() {
        res = businessClient.getSoldDealsUsages(Response.class, BusinessId);
        Collection<Dealsusagetb> usages = res.readEntity(gDUsages);
        dusages = new ArrayList<>();
        for (Dealsusagetb usage : usages) {
            if(usage.getStatus() == 1) {
                dusages.add(usage);
            }
        }
        return dusages;
    }
    
    public void check() throws IOException {
        res = businessClient.addUsage(Response.class, String.valueOf(UsageID), String.valueOf(secretCode));
        Boolean codeIsValid = Boolean.valueOf(res.readEntity(String.class));
        if(codeIsValid) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/business/UseDeals.jsf");
            codeMessage = "";
        } else {
            codeMessage = "Code is not valid! Please try again!";
        }
    }
    
    public void calculateAmount() {
        for (Dealsusagetb sdeal : sdeals) {
            RedeemAmount += sdeal.getDealID().getAverageCost();
        }
        System.out.println("Redeem Amount "+RedeemAmount);
    }
    
    public void giveUsage(int usageID) {
        secretCode = 0;
        UsageID = usageID;
    }
    
    public DealsRedeemBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        BusinessId = request.getSession().getAttribute("businessid").toString();
        businessClient = new BusinessClient(token);
        commonClient = new CommonClient();
        gDUsages = new GenericType<Collection<Dealsusagetb>>(){};
        gSDeals = new GenericType<Collection<Dealsusagetb>>(){};
    }
    
}
