/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import client.UserClient;
import entity.Carttb;
import entity.Dealspaymenttb;
import static entity.Dealspaymenttb_.userID;
import entity.Offertb;
import entity.Usertb;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    CommonClient common;
    UserClient userClient;
    Response res;
    
    GenericType<Offertb> gOffer;
    Offertb offer;
    GenericType<Collection<Carttb>> gCart;
    Collection<Carttb> cart;
    
    private int cartTotal;
    private int totalPrice;
    private String userId;

    public Collection<Carttb> getCart() {
        return cart;
    }

    public int getCartTotal() {
        return cartTotal;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Offertb getOffer() {
        return offer;
    }
    
    public void checkAndApplyOffer() {
        Object h = session.getAttribute("offer");
        if(h!=null) {
            res = common.getOfferByCode(Response.class, h.toString());
            offer = res.readEntity(gOffer);
        }
    }
    
    public void countTotal() {
        cartTotal = 0;
        res = userClient.getUserCartDeals(Response.class, userId);
        cart = res.readEntity(gCart);
        for (Carttb carttb : cart) {
            cartTotal += carttb.getDealID().getAverageCost();
        }
        totalPrice = cartTotal;
        if(session.getAttribute("offerid") != null) {
            totalPrice -= (totalPrice * Integer.parseInt(session.getAttribute("percentoff").toString())) / 100;
            totalPrice -= Integer.parseInt(session.getAttribute("dollarsoff").toString());
        }
        totalPrice += (cartTotal * 10) / 100;
        System.out.println("Carttotal = "+cartTotal+" and totalprice = "+totalPrice);
    }
    
    public String placeOrder() {
        Dealspaymenttb payment = new Dealspaymenttb();
        Usertb user = new Usertb();
        user.setUserID(Integer.parseInt(userId));
        payment.setUserID(user);
        Offertb offer = new Offertb();
        String offerId = "0";
        if(session.getAttribute("offerid") != null) {
            offerId =  session.getAttribute("offerid").toString();
        }
        offer.setOfferID(Integer.parseInt(offerId));
        payment.setOfferID(offer);
        
        System.out.println("Place order for userId "+userId+" and for offerId "+offerId);
        userClient.createPayment(payment);
        //userClient.addDealUsage(userId);
        session.removeAttribute("offerid");
        session.setAttribute("addusage", "true");
        
        return "/user2/Checkout.jsf?faces-redirect=true";
    }
    
    public void addUsage() {
        userClient.addDealUsage(userId);
        session.removeAttribute("addusage");
    }
    
    public CheckoutBean() {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = session.getAttribute("token").toString();
        
        userId = session.getAttribute("userid").toString();
        common = new CommonClient();
        userClient = new UserClient(token);
        gOffer = new GenericType<Offertb>(){};
        gCart = new GenericType<Collection<Carttb>>(){};
    }
    
}
