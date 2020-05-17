/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.UserBeanLocal;
import entity.Carttb;
import entity.Dealspaymenttb;
import entity.Dealsusagetb;
import entity.Reviewtb;
import entity.Usertb;
import java.util.Collection;
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
@Path("user")
public class UserResource {
    @EJB UserBeanLocal ubl;
    @Context
    private UriInfo context;

    public UserResource() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(Usertb u) {
        ubl.addUser(u.getName(), u.getEmail(), u.getPassword());
    }
    @PUT
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editUser(Usertb u) {
        ubl.editUser(u.getUserID(), u.getName(), u.getEmail(), u.getPassword());
    }
    
//    Review
    @POST
    @Path("/review")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addReview(Reviewtb review) {
        ubl.addReview(review.getUserID().getUserID(), review.getBussinessID().getBusinessID(), review.getRate(), review.getTitle(), review.getReview());
    }
    @PUT
    @Path("/review")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editReview(Reviewtb review) {
        ubl.editReview(review.getReviewID(), review.getRate(), review.getTitle(), review.getReview());
    }
    @DELETE
    @Path("/review/{reviewId}")
    @RolesAllowed("User")
    public void deleteReview(@PathParam("reviewId") int reviewId) {
        ubl.removeReview(reviewId);
    }
    
//    Cart
    @POST
    @Path("/cart/{userId}/{dealId}")
    @RolesAllowed("User")
    public void addDealToCart(@PathParam("userId") int userid, @PathParam("dealId") int dealid) {
        ubl.addDealToCart(userid, dealid);
    }
    @DELETE
    @Path("/cart/{cartId}")
    @RolesAllowed("User")
    public void removeDealFromCart(@PathParam("cartId") int cartId) {
        ubl.removeDealFromCart(cartId);
    }
    @GET
    @Path("/cart/{userId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Carttb> getUserCartDeals(@PathParam("userId") int uid) {
        return ubl.getUserCartDeals(uid);
    }
    @POST
    @Path("/cartcontains/{userId}/{dealId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean ifCartContainsDeal(@PathParam("userId") int userId, @PathParam("dealId") int dealId) {
        boolean result = ubl.isDealInTheCart(userId, dealId);
        return result;
    }
    
//    Payment
    @POST
    @Path("/payment")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public void createPayment(Dealspaymenttb payment) {
        ubl.makePayment(payment.getUserID().getUserID(), payment.getOfferID().getOfferID(), payment.getPaymentMode(), payment.getStatus());
    }
    @GET
    @RolesAllowed("User")
    @Path("/payment/{paymentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealspaymenttb> getUserPayments(@PathParam("paymentId") int paymentId) {
        return ubl.getPaymentDetails(paymentId);
    }
    
//    Rating
    @GET
    @RolesAllowed("User")
    @Path("/rating/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Dealsusagetb> getUserDealsRating(@PathParam("userId") int userId) {
        return ubl.getPurchasedDeals(userId);
    }
    @POST
    @Path("/rating")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    public void giveRating(Dealsusagetb usage) {
        ubl.giveRating(usage.getUserID().getUserID(), usage.getUserRating(), usage.getUserComment());
    }
}
