/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import client.UserClient;
import entity.Offertb;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public void placeOrder() {
        String userId = session.getAttribute("userid").toString();
        Object offer = session.getAttribute("offerid");
        String offerId = "";
        if(offer == null) {
            offerId = "0";
        }
        System.out.println("Place order for userId "+userId+" and for offerId "+offerId);
        session.setAttribute("offerid", null);
    }
    public CheckoutBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        common = new CommonClient();
        userClient = new UserClient(token);
        gOffer = new GenericType<Offertb>(){};
    }
    
}
