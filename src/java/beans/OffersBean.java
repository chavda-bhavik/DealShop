/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Offertb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "offersBean")
@RequestScoped
public class OffersBean {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    String offerId;
    CommonClient common;
    Response res;
    GenericType<Collection<Offertb>> gOffers;
    private Collection<Offertb> offers;
    GenericType<Offertb> gOffer;
    private Offertb offer;
    private String offerBtnText;

    public String getOfferBtnText() {
        return offerBtnText;
    }

    public Offertb getOffer() {
        return offer;
    }

    public void applyOfferCode() {
        offerBtnText = "Offer Applied";
        session.setAttribute("offercode", session.getAttribute("tempoffercode").toString());
        session.setAttribute("offerid", session.getAttribute("tempofferid").toString());
        session.setAttribute("percentoff", session.getAttribute("temppercentoff").toString());
        session.setAttribute("dollarsoff", session.getAttribute("tempdollarsoff").toString());        
    }
    
    public void redirectIfIdNotPresent() throws IOException {
        offerId = params.get("offerid");
        if(offerId == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/user2/Offers.jsf");
        }
    }
    
    public void getOfferDetails() {
        res = common.getOfferDetails(Response.class, offerId);
        offer = res.readEntity(gOffer);
        session.setAttribute("tempoffercode", offer.getCode());
        session.setAttribute("tempofferid", offer.getOfferID());
        session.setAttribute("temppercentoff", offer.getPercentOff());
        session.setAttribute("tempdollarsoff", offer.getDollarsOff());
        
        if(session.getAttribute("offerid") != null) {
            if(session.getAttribute("offerid").toString().equals(offer.getOfferID().toString())) {
                offerBtnText = "Offer Applied";
            }
        }
    }
    
    public Collection<Offertb> getOffers() {
        res = common.getAllOffers(Response.class);
        offers = res.readEntity(gOffers);
        return offers;
    }

    public void setOffers(Collection<Offertb> offers) {
        this.offers = offers;
    }
    
    public OffersBean() {
        
        offerBtnText = "Apply Offer";
        
        common = new CommonClient();
        gOffers = new GenericType<Collection<Offertb>>(){};
        offers = new ArrayList<Offertb>();
        gOffer = new GenericType<Offertb>(){};
    }
    
}
