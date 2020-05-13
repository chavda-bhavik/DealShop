/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Carttb;
import entity.Dealspaymenttb;
import entity.Dealsusagetb;
import entity.Reviewtb;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author bhavik
 */
@Local
public interface UserBeanLocal {
    //---User---
    void addUser(String Name, String Email, String Password);
    void editUser(int UserID, String Name, String Email, String Password);
    void changePassword(int UserID, String Password); // Not Need
    
    //---Review---
    void addReview(int UserID, int BussinessID, int Rate, String Title, String Review);
    void editReview(int ReviewID, int Rate, String Title, String Review);
    void removeReview(int ReviewID);
    Collection<Reviewtb> getUserReview(int UserID);
    
    //---Cart---
    void addDealToCart(int UserID, int DealID);
    void removeDealFromCart(int CartID);
    Boolean isDealInTheCart(int UserId, int DealID);
    Collection<Carttb> getUserCartDeals(int UserID);
    
    //---Payment---
    void makePayment(int UserID, int OfferID, int PaymentMode, int Stauts);
    Collection<Dealspaymenttb> getPaymentDetails(int UserID);
    Collection<Dealsusagetb> getPurchasedDeals(int UserID);
    
    //---Deal Usage Rating
    void giveRating(int UsageID, int Rating, String Comment);
}
