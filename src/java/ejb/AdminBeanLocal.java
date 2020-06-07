/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.AdminDashboard;
import entity.Redeems;
import entity.Redeemtb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bhavik
 */
@Local
public interface AdminBeanLocal {
    //---Bussiness Category---
    void addBussinessCategory(String CategoryName, String Image);
    void editBussinessCategory(int CategoryID, String CategoryName, String Image);
    void removeBussinessCategory(int CategoryID);
    
    //---Bussiness Type---
    void addBussinessType(String TypeName, int CategoryID, String Image);
    void editBussinessType(int TypeID, String TypeName, int CategoryID, String Image);
    void removeBussinessType(int TypeID);
    
    //---Bussiness Information---
    void addInformation(String InfoName);
    void editInformation(int InfoID, String InfoName);
    void removeInformation(int InfoID);
    
    //---Bussiness Links---
    void addLink(String Name, String Logo);
    void editLink(int LinkID,String Name, String Logo);
    void removeLink(int LinkID);
    
    //--Bussiness--
    void removeBussiness(int BussinessID);
    void blockBussiness(int BussinessID);
    void verifyBusinessSuccess(int BussinessID);
    void verifyDealSuccess(int DealID);
    
    //---State---
    void addState(String Name);
    void editState(int StateID, String Name);
    void removeState(int StateID);
    
    //---City---
    void addCity(String Name, int StateID);
    void editCity(int CityID, String Name, int StateID);
    void removeCity(int CityID);
    
    //-------------------------------------------------------------------------
    
    //---Deals Category---
    void addDealsCategory(String Name, String Image);
    void editDealsCategory(int CategoryID, String Name, String Image);
    void removeDealsCategory(int CategoryID);
    
    //---Deal---
    void changeDealStatus(int DealID, int Status);
    
    //---Offer---
    void addOffer(String Name, String Code, String TermsConditions, Date IssueDate, Date DueDate, String BannerImage, String OfferImage, int PercentOff, int DollarsOff);
    void editOffer(int OfferID, String Name, String Code, String TermsConditions, Date IssueDate, Date DueDate, String BannerImage, String OfferImage, int PercentOff, int DollarsOff);
    void removeOffer(int OfferID);
 
    //---Dashboard--
    AdminDashboard getDashboardData();
    
    //--Payment---   
    Collection<Redeems> getPendingRedeems();
    Collection<Redeemtb> getGivenRedeems();
    void setBusinessRedeems(int BusinessId, int Amount);
}
