/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Dealscategorytb;
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
@Named(value = "dealsCategoryBean")
@RequestScoped
public class DealsCategoryBean {
    CommonClient common;
    Response res;
    GenericType<Collection<Dealscategorytb>> gCategories;
    private Collection<Dealscategorytb> categories;

    public Collection<Dealscategorytb> getCategories() {
        res = common.getDealsCategory(Response.class);
        categories = res.readEntity(gCategories);
        return categories;
    }

    public void setCategories(Collection<Dealscategorytb> categories) {
        this.categories = categories;
    }
    
    public DealsCategoryBean() {
        common = new CommonClient();
        gCategories = new GenericType<Collection<Dealscategorytb>>(){};
        categories = new ArrayList<Dealscategorytb>();
    }
    
}
