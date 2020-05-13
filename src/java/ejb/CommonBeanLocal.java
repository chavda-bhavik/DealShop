/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
    Collection<Businesstb> getAllBusiness();
    
//    Collection<Businesstb> getAllBusinessByLocation();
    Collection<Businesstb> getAllBusinessByCity(int CityID);
    Collection<Businesstb> getAllBusinessByState(int StateID);
    Collection<Businesstb> getAllBusinessByCategory(int CategoryID);
    Businesstb getBusiness(int BusinessID);
    Collection<Businessphotostb> getBusinessPhotos(int BusinessID);
    Collection<Reviewtb> getBusinessReviews(int BusinessID);
    
    //--Information---
    Collection<Informationtb> getInformationList();
    Collection<Informationtb> getBussinessInformation(int BusinessID);
    
    //--State & City
    Collection<Statetb> getAllState();
    Collection<Citytb> getAllCity();
    
    //---Links
    Collection<Linkstb> getLinksList();
    Collection<Linkstb> getBussinessLinks(int BusinessID);
    
    //---Deals---
    Dealstb getSingleDeal(int DealID);
    Collection<Dealscategorytb> getDealsCategoryList();
    Collection<Dealstb> getBusinessDeals(int BussinessID);
    Collection<Dealstb> getDealsByCategory(int DealCategoryID);
    Collection<Dealstb> getDealsByMaxSoldNo(int limit);
    Collection<Dealstb> getDealsByRecentlyAdded();
    
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
}
