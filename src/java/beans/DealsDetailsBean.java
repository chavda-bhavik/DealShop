/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import client.UserClient;
import entity.Businessphotostb;
import entity.Businesstb;
import entity.Dealsdetailstb;
import entity.Dealsmenutb;
import entity.Dealstb;
import entity.Informationtb;
import entity.Reviewtb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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
@Named(value = "dealsDetailsBean")
@RequestScoped
public class DealsDetailsBean {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    CommonClient common;
    UserClient userClient;
    
    Response res;
    GenericType<Dealsdetailstb> gDealDetails;
    private Dealsdetailstb DealDetails;
    GenericType<Dealstb> gDeal;
    private Dealstb Deal;
    GenericType<Businesstb> gBusiness;
    private Businesstb Business;
    GenericType<Dealsmenutb> gDealMenu;
    private Dealsmenutb DealMenu;
    GenericType<Collection<Informationtb>> gInformations;
    private Collection<Informationtb> Informations;
    GenericType<Collection<Businessphotostb>> gBusinessPhotos;
    private Collection<Businessphotostb> BusinessPhotos;
    GenericType<Collection<Reviewtb>> gBusinessReviews;
    private Collection<Reviewtb> BusinessReviews;
    GenericType<Boolean> gCartContainsDeal;
    Boolean CartContainsDeal;

    public Boolean getCartContainsDeal() {
        return CartContainsDeal;
    }
    
    public Collection<Businessphotostb> getMenuPhotos() {
        Collection<Businessphotostb> menu = new ArrayList<Businessphotostb>();
        for (Businessphotostb BusinessPhoto : BusinessPhotos) {
            if(BusinessPhoto.getType() == 2) {
                menu.add(BusinessPhoto);
            }
        }
        return menu;
    }
    
    public Collection<Reviewtb> getBusinessReviews() {
        return BusinessReviews;
    }

    public Dealsmenutb getDealMenu() {
        return DealMenu;
    }

    public Collection<Informationtb> getInformations() {
        return Informations;
    }

    public Collection<Businessphotostb> getBusinessPhotos() {
        Collection<Businessphotostb> photos = new ArrayList<Businessphotostb>();
        for (Businessphotostb BusinessPhoto : BusinessPhotos) {
            if(BusinessPhoto.getType() == 1) {
                photos.add(BusinessPhoto);
            }
        }
        return photos;
    }

    public Businesstb getBusiness() {
        return Business;
    }

    public Dealstb getDeal() {
        String dealId = params.get("dealid");
        res = common.getSingleDeal(Response.class, dealId);
        Deal = res.readEntity(gDeal);
        return Deal;
    }
    
    public Dealsdetailstb getDealDetails() {
        return DealDetails;
    }
    
    public void doCartCheck() {
        String dealId = params.get("dealid");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        userClient = new UserClient(token);
        String userId = session.getAttribute("userid").toString();
        res = userClient.ifCartContainsDeal(Response.class, userId, dealId);
        CartContainsDeal = Boolean.valueOf(res.readEntity(String.class));
        System.out.println(CartContainsDeal);
    }
    
    public void fetchAllData() {
        String dealId = params.get("dealid");

        // Fetch Single Deal
        res = common.getSingleDeal(Response.class, dealId);
        Deal = res.readEntity(gDeal);

//        Object userId = session.getAttribute("userid");
//        CartContainsDeal = false;
//        System.out.println("User id in fetch all data "+userId.toString());
//        //String userId = userId.toString();
//        if(userId != null) {
//            res = userClient.ifCartContainsDeal(Response.class, "13", dealId);
//            CartContainsDeal = Boolean.valueOf(res.readEntity(String.class));
//        }
        
        // Get Deal Business
        res = common.getBusiness(Response.class, String.valueOf(Deal.getBusinessID().getBusinessID()));
        Business = res.readEntity(gBusiness);
        
        // Get Deal Details
        res = common.getDealDetails(Response.class, String.valueOf(dealId));
        DealDetails = res.readEntity(gDealDetails);
        
        // Get Deal Menu
        res = common.getDealMenu(Response.class, dealId);
        DealMenu = res.readEntity(gDealMenu);
        
        // Get Recommanded Deals
        // Get Business Information
        res = common.getBusinessInfos(Response.class, Business.getBusinessID().toString());
        Informations = res.readEntity(gInformations);
        
        // Get Business Photos
        res = common.getBusinessPhotos(Response.class, Business.getBusinessID().toString());
        BusinessPhotos = res.readEntity(gBusinessPhotos);
        
        // Get Business Reviews
        res = common.getBusinessReviews(Response.class, Business.getBusinessID().toString());
        BusinessReviews = res.readEntity(gBusinessReviews);
        
        session.setAttribute("dealId", dealId);
    }
    
    public String addDealToCart() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = "";
        token = request.getSession().getAttribute("token").toString();
        userClient = new UserClient(token);
        
        String userId = session.getAttribute("userid").toString();
        String dealId = session.getAttribute("dealId").toString();
        if(dealId != null) {
            userClient.addDealToCart(userId, dealId);
            System.out.println("Deal added to cart");
        }
        return "/user2/UserDeals.jsf?faces-redirect=true";
    }
    
    public DealsDetailsBean() {
        common = new CommonClient();

        gDealDetails = new GenericType<Dealsdetailstb>(){};
        gDeal = new GenericType<Dealstb>(){};
        gBusiness = new GenericType<Businesstb>(){};
        gDealMenu = new GenericType<Dealsmenutb>(){};
        gInformations  = new GenericType<Collection<Informationtb>>(){};
        Informations = new ArrayList<Informationtb>();
        gBusinessPhotos = new GenericType<Collection<Businessphotostb>>(){};
        BusinessPhotos = new ArrayList<Businessphotostb>();
        gBusinessReviews = new GenericType<Collection<Reviewtb>>(){};
        BusinessReviews = new ArrayList<Reviewtb>();
        gCartContainsDeal = new GenericType<Boolean>(){};
    }
}
