/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Offertb;
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
@Named(value = "offersBean")
@RequestScoped
public class OffersBean {

    CommonClient common;
    Response res;
    GenericType<Collection<Offertb>> gOffers;
    private Collection<Offertb> offers;

    public Collection<Offertb> getOffers() {
        res = common.getAllOffers(Response.class);
        offers = res.readEntity(gOffers);
        return offers;
    }

    public void setOffers(Collection<Offertb> offers) {
        this.offers = offers;
    }
    
    public OffersBean() {
        common = new CommonClient();
        gOffers = new GenericType<Collection<Offertb>>(){};
        offers = new ArrayList<Offertb>();
    }
    
}
