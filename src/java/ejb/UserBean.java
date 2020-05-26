/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Businesstb;
import entity.Carttb;
import entity.Dealspaymenttb;
import entity.Dealstb;
import entity.Dealsusagetb;
import entity.Offertb;
import entity.Reviewtb;
import entity.Usercategorytb;
import entity.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bhavik
 */
@Stateless
public class UserBean implements UserBeanLocal {
    @PersistenceContext(unitName="DealShopPU")
    EntityManager em;
    
    @Override
    public void addUser(String Name, String Email, String Password) {
        Usercategorytb ucategory = em.find(Usercategorytb.class, 2);
        Collection<Usertb> users = ucategory.getUsertbCollection();
        
        Usertb user = new Usertb();
        user.setUserCategoryID(ucategory);
        user.setEmail(Email);
        user.setName(Name);
        user.setPassword(Password);
        users.add(user);
        
        em.persist(user);
        em.merge(ucategory);
    }

    @Override
    public void editUser(int UserID, String Name, String Email, String Password) {
        Usertb user = em.find(Usertb.class, UserID);
        user.setEmail(Email);
        user.setName(Name);
        user.setPassword(Password);
        em.merge(user);
    }

    @Override
    public void changePassword(int UserID, String Password) {
        Usertb user = em.find(Usertb.class, UserID);
        user.setPassword(Password);
        em.merge(user);
    }

    @Override
    public void addReview(int UserID, int BusinessID, int Rate, String Title, String Review) {
        Usertb user = em.find(Usertb.class, UserID);
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Reviewtb> businessReviews = business.getReviewtbCollection();
        Collection<Reviewtb> userReviews = user.getReviewtbCollection();
        
        Reviewtb review = new Reviewtb();
        review.setRate(Rate);
        review.setTitle(Title);
        review.setReview(Review);
        review.setBussinessID(business);
        businessReviews.add(review);
        userReviews.add(review);
        review.setUserID(user);
        business.setReviewtbCollection(businessReviews);
        user.setReviewtbCollection(userReviews);
        
        em.merge(user);
        em.merge(business);
        em.persist(review);
    }

    @Override
    public void editReview(int ReviewID, int Rate, String Title, String Review) {
        Reviewtb review = em.find(Reviewtb.class, ReviewID);
        review.setRate(Rate);
        review.setTitle(Title);
        review.setReview(Review);
        em.merge(review);
    }

    @Override
    public void removeReview(int ReviewID) {
        Reviewtb review = em.find(Reviewtb.class, ReviewID);
        Businesstb business = review.getBussinessID();
        Collection<Reviewtb> reviews = business.getReviewtbCollection();
        reviews.remove(review);
        business.setReviewtbCollection(reviews);
        em.merge(business);
        em.remove(review);
    }

    @Override
    public Collection<Reviewtb> getUserReview(int UserID) {
        Usertb user = em.find(Usertb.class, UserID);
        return user.getReviewtbCollection();
    }

    @Override
    public void addDealToCart(int UserID, int DealID) {
        Usertb user = em.find(Usertb.class, UserID);
        Collection<Carttb> userCart = user.getCarttbCollection();
        boolean cartContainsDeal = false;
        
        for(Carttb ucart:userCart) {
            if(ucart.getDealID().getDealID() == DealID) {
                cartContainsDeal = true;
                break;
            }
        }
        if(!cartContainsDeal) {
            Dealstb deal = em.find(Dealstb.class, DealID);
            Collection<Carttb> dealCart = deal.getCarttbCollection();

            Date addDate = new Date();
            Carttb cart = new Carttb();
            cart.setDealID(deal);
            cart.setUserID(user);
            cart.setAddDate(addDate);
            cart.setIsPaid(1);

            userCart.remove(cart);
            dealCart.remove(cart);
            user.setCarttbCollection(userCart);
            deal.setCarttbCollection(dealCart);

            em.merge(user);
            em.merge(deal);
            em.persist(cart);   
        }
    }

    @Override
    public void removeDealFromCart(int CartID) {
        Carttb cart = em.find(Carttb.class, CartID);
        Usertb user = cart.getUserID();
        Collection<Carttb> userCart = user.getCarttbCollection();
        Dealstb deal = cart.getDealID();
        Collection<Carttb> dealCart = deal.getCarttbCollection();
        
        userCart.remove(cart);
        dealCart.remove(cart);
        user.setCarttbCollection(userCart);
        deal.setCarttbCollection(dealCart);
        
        em.merge(user);
        em.merge(cart);
        em.remove(cart);
    }

    @Override
    public Collection<Carttb> getUserCartDeals(int UserID) {
        Usertb user = em.find(Usertb.class, UserID);
        return user.getCarttbCollection();
    }

    @Override
    public Boolean isDealInTheCart(int UserId, int DealID) {
        Usertb user = em.find(Usertb.class, UserId);
        Collection<Carttb> userCart = user.getCarttbCollection();
        boolean cartContains = false;
        for(Carttb cart:userCart) {
            if(cart.getDealID().getDealID() == DealID) {
                cartContains = true;
                break;
            }
        }
        return cartContains;
    }

    @Override
    public void makePayment(int UserID, int OfferID, int PaymentMode, int Status) {
        Usertb user = em.find(Usertb.class, UserID);
        Collection<Dealspaymenttb> userPayments = user.getDealspaymenttbCollection();
        Offertb offer = em.find(Offertb.class, OfferID);
        
        Date paymentDate = new Date();
        Dealspaymenttb payment = new Dealspaymenttb();
        payment.setOfferID(offer);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMode(6);
        payment.setUserID(user);
        payment.setStatus(2);
        payment.setIsEntered(1);
        userPayments.add(payment);
        //offerPayments.add(payment);
        user.setDealspaymenttbCollection(userPayments);
        //offer.setDealspaymenttbCollection(offerPayments);
        
//        em.persist(payment);

        Collection<Carttb> userCartCollection = user.getCarttbCollection();
        Collection<Dealsusagetb> userDealsCollection = user.getDealsusagetbCollection();
        for(Carttb cart : userCartCollection) {
            Dealsusagetb dUsage = new Dealsusagetb();
            dUsage.setDealID(cart.getDealID());
            dUsage.setUserID(user);
            dUsage.setStatus(1);
            userDealsCollection.add(dUsage);
            em.persist(dUsage);
            
            userCartCollection.remove(cart);
            em.remove(cart);
        }
        user.setDealsusagetbCollection(userDealsCollection);
        user.setCarttbCollection(userCartCollection);
        em.merge(user);
        em.persist(payment);
    }
    
    @Override
    public void addDealsUsage(int userid) {
        Usertb user = em.find(Usertb.class, userid);
        //Dealspaymenttb payment = (Dealspaymenttb) em.createNamedQuery("Dealspaymenttb.findByIsEnteredUserID").setParameter("isEntered", 1).setParameter("userID", user).getResultList().get(0);
        Collection<Carttb> userCartCollection = user.getCarttbCollection();
        Collection<Dealsusagetb> userDealsCollection = user.getDealsusagetbCollection();
        //Collection<Dealsusagetb> paymentDealsUsage = payment.getDealsusagetbCollection();
        for(Carttb cart : userCartCollection) {
            Dealsusagetb dUsage = new Dealsusagetb();
            dUsage.setDealID(cart.getDealID());
            //dUsage.setPaymentID(payment);
            dUsage.setUserID(user);
            dUsage.setStatus(1);
            //cart.setIsPaid(2);
            //paymentDealsUsage.add(dUsage);
            userDealsCollection.add(dUsage);
            em.persist(dUsage);
            //em.merge(cart);
            userCartCollection.remove(cart);
            em.remove(cart);
        }
        //payment.setIsEntered(2);
        //payment.setDealsusagetbCollection(paymentDealsUsage);
        user.setDealsusagetbCollection(userDealsCollection);
        user.setCarttbCollection(userCartCollection);
        em.merge(user);
        //em.merge(payment);
    }

    @Override
    public Collection<Dealspaymenttb> getPaymentDetails(int UserID) {
        Usertb user = em.find(Usertb.class, UserID);
        return user.getDealspaymenttbCollection();
    }

    @Override
    public Collection<Dealsusagetb> getPurchasedDeals(int UserID) {
        Usertb user = em.find(Usertb.class, UserID);
        return user.getDealsusagetbCollection();
    }

    @Override
    public void giveRating(int UsageID, int Rating, String Comment) {
        Dealsusagetb dUsage = em.find(Dealsusagetb.class, UsageID);
        dUsage.setUserRating(Rating);
        dUsage.setUserComment(Comment);
        em.merge(dUsage);
    }
}
