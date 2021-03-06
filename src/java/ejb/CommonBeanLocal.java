/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Businesscategorytb;
import entity.Businesslinkstb;
import entity.Businessphotostb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Dealphotostb;
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
import javax.ejb.Local;

/**
 *
 * @author bhavik
 */
@Local
public interface CommonBeanLocal {
    //---Information---
    Collection<Businesscategorytb> getAllBusinessCategories();
    Collection<Businesstypetb> getAllBusinessTypes();
    Collection<Businesstypetb> getBussinessTypesByCategory(int CategoryId);
    Collection<Businesstb> getAllBusiness();
    
    // Restricted unverified Businesses and Deals for user
    Collection<Businesstb> getAllBusinessByCityUser(int CityID);
    Collection<Dealstb> getAllDealsOfBusinessUser(int BusinessID);
    Collection<Dealstb> getLatestDealsUser(int Start, int Limit);
    Collection<Dealstb> getDealsByCategoryUser(int CategoryID);
    Collection<Dealstb> getTrandingDealsUser(int Limit);
    
//    Collection<Businesstb> getAllBusinessByLocation();
    Collection<Businesstb> getAllBusinessByCity(int CityID);
    Collection<Businesstb> getAllBusinessByState(int StateID);
    Collection<Businesstb> getAllBusinessByCategory(int CategoryID);
    Businesstb getBusiness(int BusinessID);
    Businesstb getBusinessByUserEmail(String UserEmailId);
    Collection<Businessphotostb> getBusinessPhotos(int BusinessID);
    Collection<Reviewtb> getBusinessReviews(int BusinessID);
    
    //--Information---
    Collection<Informationtb> getInformationList();
    Collection<Informationtb> getBussinessInformation(int BusinessID);
    
    //--State & City
    Collection<Statetb> getAllState();
    Collection<Citytb> getAllCity();
    Collection<Citytb> getCitiesByState(int stateId);
    
//---Links
    Collection<Linkstb> getLinksList();
    Collection<Businesslinkstb> getBussinessLinks(int BusinessID);
    
    //---Deals---
    Dealstb getSingleDeal(int DealID);
    Collection<Dealstb> getAllDeals();
    Collection<Dealscategorytb> getDealsCategoryList();
    Collection<Dealstb> getBusinessDeals(int BussinessID);
    Collection<Dealstb> getDealsByCategory(int DealCategoryID);
    Collection<Dealstb> getDealsByMaxSoldNo(int limit);
    Collection<Dealstb> getDealsByRecentlyAdded();
    Collection<Dealphotostb> getDealPhotos(int DealID);
    
    //---Deals Details---
    Dealsdetailstb getDealDetails(int DealID);
    
    //---Deals Menu---
    Dealsmenutb getDealMenu(int DealID);
 
    //---Offers---
    Collection<Offertb> getAllOffers();
    Offertb getOfferByID(int OfferID);
    Offertb getOfferByCode(String Code);
    Boolean isOfferExists(String Code);
    
    //---Login---
    Usertb login(String Email, String Password);
    Usertb getLoginUser(String Email);
    void registerUser(String name, String Email, String Password, int type);
    Boolean checkEmailExists(String Email);
}
