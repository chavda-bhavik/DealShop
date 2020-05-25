/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.BusinessBeanLocal;
import entity.Businesstb;
import entity.Dealsdetailstb;
import entity.Dealsmenutb;
import entity.Dealspaymenttb;
import entity.Dealstb;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author bhavik
 */
@Path("business")
@DeclareRoles("Business")
public class BusinessResource {
    @EJB BusinessBeanLocal bbl;
    
    @Context
    private UriInfo context;

    public BusinessResource() {
    }
    
    // Business
    @POST
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerBusiness(Businesstb b) {
        bbl.registerBusiness(b.getBusinessName(), b.getEmailID(), b.getAddress(), b.getCustomerCarePhoneNo(), b.getReservationPhoneNo(), b.getDaysOfOperation(), b.getHoursOfOperation(), b.getLocation(), b.getNeedToKnow(), b.getAwardsRecognition(), b.getBusinessCategoryID().getCategoryID(), b.getBusinessTypeID().getBusinessTypeID(), b.getStateID().getStateID(), b.getCityID().getCityID(), b.getUserID().getUserID());
    }
    @PUT
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editBusiness(Businesstb b) {
        System.out.println("edit gone to");
        bbl.editBusinessProfile(b.getBusinessID(), b.getBusinessName(), b.getEmailID(), b.getAddress(), b.getCustomerCarePhoneNo(), b.getReservationPhoneNo(), b.getDaysOfOperation(), b.getHoursOfOperation(), b.getLocation(), b.getNeedToKnow(), b.getAwardsRecognition(), b.getBusinessCategoryID().getCategoryID(), b.getBusinessTypeID().getBusinessTypeID(), b.getStateID().getStateID(), b.getCityID().getCityID());
    }
    @DELETE
    @Path("/{bid}")
    @RolesAllowed("Business")
    public void removeBusiness(@PathParam("bid") int bid) {
        bbl.removeBusiness(bid);
    }
    @POST
    @Path("/submit/{bid}")
    @RolesAllowed("Business")
    public void submitBusiness(@PathParam("bid") int bid) {
        bbl.submitBusiness(bid);
    }
    
    // Business Information
    @POST
    @Path("/info/{id}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setBusinessInfo(@PathParam("id") int id, Collection<Integer> InfoIDs) {
        bbl.setBusinessInfo(id, InfoIDs);
    }
    
    // Business Photos
    @POST
    @Path("/photos/{bid}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setBusinessPhotos(@PathParam("bid") int id, Collection<String> photos) {
        bbl.addBusinessPhotos(id, photos);
    }
    @DELETE
    @Path("/businessphotos/{bid}")
    @RolesAllowed("Business")
    public void removeBusinessPhoto(@PathParam("bid") int id) {
        System.out.println("Resource "+id);
        //bbl.removeBussinessPhoto(id);
    }
    @POST
    @Path("/links/{bid}/{linkId}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setBusinessLinks(@PathParam("bid") int bid, @PathParam("linkId") int LinkId, String Link) {
        bbl.setBusinessLinks(bid, LinkId, Link);
    }
    
    // Deals
    @POST
    @Path("/deals/{bid}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBusinessDeal(@PathParam("bid") int bid, Dealstb d) {
        bbl.addDeal(bid, d.getName(), d.getIssueDate(), d.getDueDate(), d.getAverageCost(), d.getDealsCategoryID().getCategoryID(), d.getBannerImage());
    }
    @PUT
    @Path("/deals")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editDeal(Dealstb d) {
        bbl.editDeal(d.getDealID(), d.getName(), d.getIssueDate(), d.getDueDate(), d.getAverageCost(), d.getDealsCategoryID().getCategoryID(), d.getBannerImage());
    }
    @DELETE
    @Path("/deals/{did}")
    @RolesAllowed("Business")
    public void removeDeal(@PathParam("did") int did) {
        bbl.removeDeal(did);
    }
    
    @POST
    @Path("/submitdeal/{dealId}")
    @RolesAllowed("Business")
    public void submitDeal(@PathParam("dealId") int dealId) {
        bbl.submitDeal(dealId);
    }
    
    @POST
    @Path("/dealphotos/{dealid}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setDealPhotos(@PathParam("dealid") int id, Collection<String> photos) {
        bbl.addDealPhotos(id, photos);
    }
    @DELETE
    @Path("/dealphotos/{photoid}")
    @RolesAllowed("Business")
    public void removeDealPhoto(@PathParam("photoid") int id) {
        bbl.removeDealPhoto(id);
    }
    
    @GET
    @Path("/deals/{businessid}")
    @RolesAllowed("Business")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealstb> getAllDeals(@PathParam("businessid") int bid) {
        return bbl.getBusinessDeals(bid);
    }
    @GET
    @Path("/dealsPayments/{did}")
    @RolesAllowed("Business")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealspaymenttb> getDealsPayments(@PathParam("did") int did) {
        return bbl.getDealPayments(did);
    }
    
    // Deal Details
    @POST
    @Path("/dealDetails/{did}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDealDetails(@PathParam("did") int did, Dealsdetailstb detail) {
        bbl.addDealDetails(did, detail.getCanncellationAllowed(), detail.getHowToUse(), detail.getThingsToRemember(), detail.getInclusion(), detail.getValidFor(), detail.getValidOn());
    }
    @PUT
    @Path("/dealDetails/{did}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editDealDetails(@PathParam("did") int ddid, Dealsdetailstb detail) {
        bbl.editDealDetails(ddid, detail.getCanncellationAllowed(), detail.getHowToUse(), detail.getThingsToRemember(), detail.getInclusion(), detail.getValidFor(), detail.getValidOn());
    }    
    @DELETE
    @Path("/dealDetails/{did}")
    @RolesAllowed("Business")
    public void removeDealDetails(@PathParam("did") int ddid) {
        bbl.removeDealDetails(ddid);
    }
    
    // Deal Menu
    @POST
    @Path("/dealMenu/{dealid}")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDealMenu(@PathParam("dealid") int dealid, Dealsmenutb menu) {
        bbl.addDealMenu(dealid, menu.getMenuData());
    }
    @PUT
    @Path("/dealMenu")
    @RolesAllowed("Business")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editDealMenu(Dealsmenutb menu) {
        bbl.editDealMenu(menu.getMenuID(), menu.getMenuData());
    }
    @DELETE
    @Path("/dealMenu/{menuid}")
    @RolesAllowed("Business")
    public void removeDealMenu(@PathParam("menuid") int menuid) {
        bbl.removeDealMenu(menuid);
    }
    
    // Deal Usage
    @POST
    @Path("/dealUsage/{dealid}")
    @RolesAllowed("Business")
    public void changeDealUsage(@PathParam("dealid") int dealid) {
        bbl.changeUsageStatus(dealid);
    }
}
