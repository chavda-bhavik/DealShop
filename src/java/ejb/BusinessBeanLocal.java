/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Dealphotostb;
import entity.Dealspaymenttb;
import entity.Dealstb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bhavik
 */
@Local
public interface BusinessBeanLocal {
    //--Business--
    void registerBusiness(String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID, int UserID);
    void editBusinessProfile(int BusinessID, String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID);
    void removeBusiness(int BusinessID);
    
    //---Bussiness Information---
    void setBusinessInfo(int BusinessID, Collection<Integer> InfoIDs);
//    void addBusinessInfo(int BusinessID, Collection<Integer> InfoIds);
//    void editBusinessInfo(int BusinessID, Collection<Integer> InfoIds);
//    void removeBussinessInfo(int BussinessID, Collection<Integer> InfoIDs);
    
    //---Bussiness Photos---
    void addDealPhotos(int DealID, Collection<String> photos);
    void removeDealPhoto(int PhotoID);
//    Collection<Dealphotostb> getDealPhotos(int DealID);
    
    void addBusinessPhotos(int BusinessID, Collection<String> photos);
    void removeBussinessPhotos(int BusinessID, Integer photos);
    
    //---Bussiness Links---
    void setBusinessLinks(int BusinessID, int LinkId, String Link);
//    void addBussinessLinks(int BusinessID, HashMap<Integer, String> links);
//    void editBussinessLinks(int BusinessID, HashMap<Integer, String> links);
//    void removeBussinessLinks(int BusinessID, Collection<Integer> links);
    
    
    //-------------------------------------------------------------------------
    
    //---Deals---
    void addDeal(int BusinessID, String Name, Date IssueDate, Date DueDate, int AverageCost, int DealCategoryID, String BannerImage);
    void editDeal(int DealID, String Name, Date IssueDate, Date DueDate, int AverageCost, int DealCategoryID, String BannerImage);
    void removeDeal(int DealID);
    Collection<Dealstb> getBusinessDeals(int BussinessID);
    Collection<Dealspaymenttb> getDealPayments(int DealID);
    void ChangeDealStatus(int DealDetailID, int Status, String UseDate);
    void submitDeal(int DealID);
    
    //---Deals Details---
    void addDealDetails(int DealID, Boolean CancellationAllowed, String HowToUse, String ThingsToRemember, String Inclusion, int ValidFor, String ValidOn);
    void editDealDetails(int DealDetailsID, Boolean CancellationAllowed, String HowToUse, String ThingsToRemember, String Inclusion, int ValidFor, String ValidOn);
    void removeDealDetails(int DealDetailsID);
    
    //---Deals Menu---
    void addDealMenu(int DealID, String MenuData);
    void editDealMenu(int MenuID, String MenuData);
    void removeDealMenu(int MenuID);
    
    //---Deals Usage
    void changeUsageStatus(int DealUsageID);
}
