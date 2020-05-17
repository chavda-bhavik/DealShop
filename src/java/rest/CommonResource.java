/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.BusinessBeanLocal;
import ejb.CommonBeanLocal;
import entity.Businesscategorytb;
import entity.Businessphotostb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Dealscategorytb;
import entity.Dealsdetailstb;
import entity.Dealsmenutb;
import entity.Dealstb;
import entity.Informationtb;
import entity.Linkstb;
import entity.Offertb;
import entity.Reviewtb;
import entity.Statetb;
import entity.Usertb;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author bhavik
 */
@Path("common")
public class CommonResource {
    @EJB CommonBeanLocal common;
    @EJB BusinessBeanLocal bbl;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CommonResource
     */
    public CommonResource() {
    }
    
    //  Information
    @GET
    @Path("/businessCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesscategorytb> getBusinessCategory() {
        return common.getAllBusinessCategories();
    }
    @GET
    @Path("/businessType")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstypetb> getAllBusinessTypes() {
        return common.getAllBusinessTypes();
    }
    @GET
    @Path("/businessTypesByCategory/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstypetb> getBussinessTypesByCategory(@PathParam("categoryId") int categoryId) {
        return common.getBussinessTypesByCategory(categoryId);
    }
    @GET
    @Path("/businesses")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstb> getAllBusiness() {
        return common.getAllBusiness();
    }
    @GET
    @Path("/businessByCity/{cityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstb> getBusinessByCity(@PathParam("cityId") int cityId) {
        return common.getAllBusinessByCity(cityId);
    }
    @GET
    @Path("/businessByState/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstb> getBusinessByState(@PathParam("stateId") int stateId)  {
        return common.getAllBusinessByState(stateId);
    }
    @GET
    @Path("/businessByCategory/{cId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstb> getAllBusinessByCategory(@PathParam("cId") int CategoryID) {
        return common.getAllBusinessByCategory(CategoryID);
    }
    @GET
    @Path("/business/{bId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Businesstb getBusiness(@PathParam("bId") int BusinessID) {
        return common.getBusiness(BusinessID);
    }
    @GET
    @Path("/businessPhotos/{bId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businessphotostb> getBusinessPhotos(@PathParam("bId") int BusinessID) {
        return common.getBusinessPhotos(BusinessID);
    }
    @GET
    @Path("/businessReviews/{bId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Reviewtb> getBusinessReviews(@PathParam("bId") int BusinessID) {
        return common.getBusinessReviews(BusinessID);
    }
    
    // Information
    @GET
    @Path("/businessInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Informationtb> getBusinessInfoList() {
        return common.getInformationList();
    }
    @GET
    @Path("/businessInfo/{bid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Informationtb> getBusinessInfos(@PathParam("bid") int bid) {
        return common.getBussinessInformation(bid);
    }
    
    //  State & City
    @GET
    @Path("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Statetb> getAllStates() {
        return common.getAllState();
    }
    @GET
    @Path("/city")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Citytb> getAllCities() {
        return common.getAllCity();
    }
    
    @GET
    @Path("/cityByState/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Citytb> getCitiesByState(@PathParam("stateId") int stateId) {
        return common.getCitiesByState(stateId);
    }
    
    //  Links
    @GET
    @Path("/businessLinks")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Linkstb> getLinks() {
        return common.getLinksList();
    }
    
    @GET
    @Path("/businessLinks/{bid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Linkstb> getBusinessLinks(@PathParam("bid") int id) {
        return common.getBussinessLinks(id);
    }
    
    //  Deal
    @GET
    @Path("/deal/{dealId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dealstb getSingleDeal(@PathParam("dealId") int DealId) {
        Dealstb deal = common.getSingleDeal(DealId);
        return deal;
    }
    
    //  DealsCategory
    @GET
    @Path("/dealsCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealscategorytb> getDealsCategory() {
        return common.getDealsCategoryList();
    }
    
    @GET
    @Path("/businessDeals/{bid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealstb> getBusinessDeals(@PathParam("bid") int id) {
        return common.getBusinessDeals(id);
    }
   
    @GET
    @Path("/dealsCategory/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealstb> getDealsByCategory(@PathParam("cid") int id) {
        return common.getDealsByCategory(id);
    }
    
    @GET
    @Path("/dealsBySoldNo/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealstb> getDealsBySoldNo(@PathParam("limit") int limit) {
        return common.getDealsByMaxSoldNo(limit);
    }
    
    @GET
    @Path("/dealsByDueDate")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealstb> getDealsByDueDate() {
        return common.getDealsByRecentlyAdded();
    }
    
    // DealDetails
    @GET
    @Path("/dealDetail/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dealsdetailstb getDealDetails(@PathParam("did") int id) {
        return common.getDealDetails(id);
    }
    
    //  DealsMenu
    @GET
    @Path("/dealMenu/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dealsmenutb getDealMenu(@PathParam("did") int id) {
        return common.getDealMenu(id);
    }
    
    //  Offer
    @GET
    @Path("/offer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Offertb> getAllOffers() {
        return common.getAllOffers();
    }
    @GET
    @Path("/offer/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Offertb getOfferDetails(@PathParam("oid") int oid) {
        return common.getOfferByID(oid);
    }
    
    @GET
    @Path("/checkOffer/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean isOfferExists(@PathParam("code") String Code) {
        return common.isOfferExists(Code);
    }
    
    @GET
    @Path("/getOffer/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Offertb getOfferByCode(@PathParam("code") String Code) {
        return common.getOfferByCode(Code);
    }
    //   Login
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usertb login(Usertb user) {
        return common.login(user.getEmail(), user.getPassword());
    }
    
    @GET
    @Path("/user/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usertb getLoginUser(@PathParam("email") String Email) {
        return common.getLoginUser(Email);
    }
    
    @POST
    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerCommonUser(Usertb user) {
        common.registerUser(user.getName(), user.getEmail(), user.getPassword(), 2);
    }
    
    @POST
    @Path("/registerBusinessUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerBusinessUser(Usertb user) {
        common.registerUser(user.getName(), user.getEmail(), user.getPassword(), 3);
    }
    
    // Business
    @POST
    @Path("/registerBusiness")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerBusiness(Businesstb b) {
        bbl.registerBusiness(b.getBusinessName(), b.getEmailID(), b.getAddress(), b.getCustomerCarePhoneNo(), b.getReservationPhoneNo(), b.getDaysOfOperation(), b.getHoursOfOperation(), b.getLocation(), b.getNeedToKnow(), b.getAwardsRecognition(), b.getBusinessCategoryID().getCategoryID(), b.getBusinessTypeID().getBusinessTypeID(), b.getStateID().getStateID(), b.getCityID().getCityID(), b.getUserID().getUserID());
    }
    
    @POST
    @Path("/checkEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean checkEmailExists(@PathParam("email") String email) {
        return common.checkEmailExists(email);
    }
    
}