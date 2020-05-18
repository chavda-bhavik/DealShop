/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Businesscategorytb;
import entity.Businessinfotb;
import entity.Businesslinkstb;
import entity.Businessphotostb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Dealscategorytb;
import entity.Dealsdetailstb;
import entity.Dealsmenutb;
import entity.Dealspaymenttb;
import entity.Dealstb;
import entity.Dealsusagetb;
import entity.Informationtb;
import entity.Linkstb;
import entity.Statetb;
import entity.Usertb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bhavik
 */
@Stateless
public class BusinessBean implements BusinessBeanLocal {
    @PersistenceContext(unitName="DealShopPU")
    EntityManager em;
    
    @Override
    public void registerBusiness(String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID, int UserID) {
        
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstb> categories = bcategory.getBusinesstbCollection();
        
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);
        Collection<Businesstb> types = btype.getBusinesstbCollection();
        
        Statetb state = em.find(Statetb.class, StateID);
        Collection<Businesstb> states = state.getBusinesstbCollection();
        
        Citytb city = em.find(Citytb.class, CityID);
        Collection<Businesstb> cities = city.getBusinesstbCollection();
        
        Usertb user = em.find(Usertb.class, UserID);
        Collection<Businesstb> users = user.getBusinesstbCollection();
        
        Businesstb business = new Businesstb();
        business.setBusinessName(Name);
        business.setEmailID(EmailID);
        business.setAddress(Address);
        business.setCustomerCarePhoneNo(CustomerCareNumber);
        business.setReservationPhoneNo(ReservationNumber);
        business.setDaysOfOperation(DaysOfOperation);
        business.setHoursOfOperation(HoursOfOperation);
        business.setLocation(Location);
        business.setNeedToKnow(NeedToKnow);
        business.setAwardsRecognition(AwardsRecognition);
        business.setBusinessCategoryID(bcategory);
        business.setBusinessTypeID(btype);
        business.setCityID(city);
        business.setStateID(state);
        business.setUserID(user);
        business.setIsVerified(1);
        
        categories.add(business);
        types.add(business);
        states.add(business);
        cities.add(business);
        users.add(business);
        
        em.persist(business);
        em.merge(bcategory);
        em.merge(btype);
        em.merge(city);
        em.merge(state);
    }

    @Override
    public void editBusinessProfile(int BusinessID, String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID) {
        System.out.println("edit from bean");
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstb> categories = bcategory.getBusinesstbCollection();
        
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);
        Collection<Businesstb> types = btype.getBusinesstbCollection();
        
        Statetb state = em.find(Statetb.class, StateID);
        Collection<Businesstb> states = state.getBusinesstbCollection();
        
        Citytb city = em.find(Citytb.class, CityID);
        Collection<Businesstb> cities = city.getBusinesstbCollection();
        
        Businesstb business = em.find(Businesstb.class, BusinessID);
        business.setBusinessName(Name);
        business.setEmailID(EmailID);
        business.setAddress(Address);
        business.setCustomerCarePhoneNo(CustomerCareNumber);
        business.setReservationPhoneNo(ReservationNumber);
        business.setDaysOfOperation(DaysOfOperation);
        business.setHoursOfOperation(HoursOfOperation);
        business.setLocation(Location);
        business.setNeedToKnow(NeedToKnow);
        business.setAwardsRecognition(AwardsRecognition);
        business.setBusinessCategoryID(bcategory);
        business.setBusinessTypeID(btype);
        business.setCityID(city);
        business.setStateID(state);

        if(business.getStateID() != state) {
            Statetb oldState = business.getStateID();
            Collection<Businesstb> oldStateBusinesses = oldState.getBusinesstbCollection();
            oldStateBusinesses.remove(business);
            oldState.setBusinesstbCollection(oldStateBusinesses);
            em.merge(oldState);
        }
        if(business.getCityID() != city) {
            Citytb oldCity = business.getCityID();
            Collection<Businesstb> oldCityBusinesses = oldCity.getBusinesstbCollection();
            oldCityBusinesses.remove(business);
            oldCity.setBusinesstbCollection(oldCityBusinesses);
            em.merge(oldCity);
        }
        if(business.getBusinessCategoryID() != bcategory) {
            Businesscategorytb oldBCategory = business.getBusinessCategoryID();
            Collection<Businesstb> oldBCategBusinesses = oldBCategory.getBusinesstbCollection();
            oldBCategBusinesses.remove(business);
            oldBCategory.setBusinesstbCollection(oldBCategBusinesses);
            em.merge(oldBCategory);
        }
        if(business.getBusinessTypeID() != btype) {
            Businesstypetb oldBType = business.getBusinessTypeID();
            Collection<Businesstb> oldBTypeBusinesses = oldBType.getBusinesstbCollection();
            oldBTypeBusinesses.remove(business);
            oldBType.setBusinesstbCollection(oldBTypeBusinesses);
            em.merge(oldBType);
        }
        
        em.merge(business);
        categories.add(business);
        em.merge(bcategory);
        types.add(business);
        em.merge(btype);
        cities.add(business);
        em.merge(city);
        states.add(business);
        em.merge(state);
    }

    @Override
    public void removeBusiness(int BusinessID) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        
            Statetb oldState = business.getStateID();
            Collection<Businesstb> oldStateBusinesses = oldState.getBusinesstbCollection();
            oldStateBusinesses.remove(business);
            oldState.setBusinesstbCollection(oldStateBusinesses);
            em.merge(oldState);

            Citytb oldCity = business.getCityID();
            Collection<Businesstb> oldCityBusinesses = oldCity.getBusinesstbCollection();
            oldCityBusinesses.remove(business);
            oldCity.setBusinesstbCollection(oldCityBusinesses);
            em.merge(oldCity);

            Businesscategorytb oldBCategory = business.getBusinessCategoryID();
            Collection<Businesstb> oldBCategBusinesses = oldBCategory.getBusinesstbCollection();
            oldBCategBusinesses.remove(business);
            oldBCategory.setBusinesstbCollection(oldBCategBusinesses);
            em.merge(oldBCategory);

            Businesstypetb oldBType = business.getBusinessTypeID();
            Collection<Businesstb> oldBTypeBusinesses = oldBType.getBusinesstbCollection();
            oldBTypeBusinesses.remove(business);
            oldBType.setBusinesstbCollection(oldBTypeBusinesses);
            em.merge(oldBType);
            
            Usertb user = em.find(Usertb.class, business.getUserID().getUserID());
            Collection<Businesstb> businesses = user.getBusinesstbCollection();
            businesses.remove(business);
            user.setBusinesstbCollection(businesses);
            em.merge(user);
        
        em.remove(business);
    }

    @Override
    public void setBusinessInfo(int BusinessID, Collection<Integer> InfoIDs) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Businessinfotb> oldInformations = business.getBusinessinfotbCollection();
        Collection<Businessinfotb> newInformations = new ArrayList<Businessinfotb>();
        
        for (Businessinfotb bInfo : oldInformations) {
            em.remove(bInfo);
        }
        
        Iterator it = InfoIDs.iterator();
        Integer InfoId;
        while(it.hasNext()) {
            InfoId = (Integer) it.next();
            Informationtb info = em.find(Informationtb.class, InfoId);
            Businessinfotb bInfo = new Businessinfotb();
            bInfo.setBussinessID(business);
            bInfo.setInformationID(info);
            em.persist(bInfo);
            newInformations.add(bInfo);
        }
        
        business.setBusinessinfotbCollection(newInformations);
        em.merge(business);
    }

    @Override
    public void addBusinessPhotos(int BusinessID, Collection<String> photos) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Businessphotostb> bphotos = business.getBusinessphotostbCollection();
        
        Iterator iterator = photos.iterator();
        while(iterator.hasNext()) {
            String photo = (String) iterator.next();
            Businessphotostb bphoto = new Businessphotostb();
            bphoto.setPhoto(photo);
            bphoto.setBusinessID(business);
            bphoto.setType(1);
            
            em.persist(bphoto);
            bphotos.add(bphoto);
        }
        
        business.setBusinessphotostbCollection(bphotos);
        em.merge(business);
    }

    @Override
    public void removeBussinessPhotos(int BusinessID, Integer photoId) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Businessphotostb> bphotos = business.getBusinessphotostbCollection();
        
        //Iterator iterator = photoIds.iterator();
        //while(iterator.hasNext()) {
            //Integer photoId = (Integer) iterator.next();
            Businessphotostb photo = em.find(Businessphotostb.class, photoId);
            bphotos.remove(photo);
            em.remove(photo);
        //}
        
        business.setBusinessphotostbCollection(bphotos);
        em.merge(business);
    }

    @Override
    public void setBusinessLinks(int BusinessID, int LinkId, String Link) {
        
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Businesslinkstb> links = business.getBusinesslinkstbCollection();
        
        Boolean linkExists = false;
        for (Businesslinkstb link : links) {
            if(link.getLinkID().getLinkID() == LinkId) {
                link.setLink(Link);
                linkExists = true;
                break;
            }
        }
        
        if(!linkExists) {
            Linkstb link = em.find(Linkstb.class, LinkId);
            Businesslinkstb bLink = new Businesslinkstb();
            bLink.setLinkID(link);
            bLink.setLink(Link);
            bLink.setBussinessID(business);
            em.persist(bLink);
            links.add(bLink);
        }
        business.setBusinesslinkstbCollection(links);
        em.merge(business);
        //Collection<Businesslinkstb> newLinks = new ArrayList<>();
        
//        for (Businesslinkstb bLink : oldLinks) {
//            em.remove(bLink);
//        }
        
//        for (Map.Entry next : links.entrySet()) {
//            Linkstb link = em.find(Linkstb.class, (Integer) next.getKey());
//            
//            Businesslinkstb bLink = new Businesslinkstb();
//            bLink.setBussinessID(business);
//            bLink.setLink((String) next.getValue());
//            bLink.setLinkID(link);
//            em.persist(bLink);
//            newLinks.add(bLink);
//        }
        
        business.setBusinesslinkstbCollection(links);
        em.merge(business);
    }

    @Override
    public void addDeal(int BusinessID, String Name, Date IssueDate, Date DueDate, int AverageCost, int DealCategoryID, String BannerImage) {
        Dealscategorytb dealCategory = em.find(Dealscategorytb.class, DealCategoryID);
        Businesstb business = em.find(Businesstb.class, BusinessID);
        Collection<Dealstb> categoryDeals = dealCategory.getDealstbCollection();
        
        Dealstb deal = new Dealstb();
        deal.setName(Name);
        deal.setIssueDate(IssueDate);
        deal.setDueDate(DueDate);
        deal.setAverageCost(AverageCost);
        deal.setBannerImage(BannerImage);
        deal.setDealsCategoryID(dealCategory);
        deal.setBusinessID(business);
        deal.setIsVerified(1);
        categoryDeals.add(deal);
        
        em.persist(deal);
        em.merge(dealCategory);
    }

    @Override
    public void editDeal(int DealID, String Name, Date IssueDate, Date DueDate, int AverageCost, int DealCategoryID, String BannerImage) {
        Dealscategorytb dealCategory = em.find(Dealscategorytb.class, DealCategoryID);
        Collection<Dealstb> categoryDeals = dealCategory.getDealstbCollection();
        
        Dealstb deal = em.find(Dealstb.class, DealID);
        deal.setName(Name);
        deal.setIssueDate(IssueDate);
        deal.setDueDate(DueDate);
        deal.setAverageCost(AverageCost);
        deal.setBannerImage(BannerImage);
        deal.setDealsCategoryID(dealCategory);
        if(deal.getDealsCategoryID().getCategoryID() != DealCategoryID) {
            Dealscategorytb oldCategory = em.find(Dealscategorytb.class, deal.getDealsCategoryID().getCategoryID());
            Collection<Dealstb> oldCategoryDeals = oldCategory.getDealstbCollection();
            oldCategoryDeals.remove(deal);
            oldCategory.setDealstbCollection(oldCategoryDeals);
            em.merge(oldCategory);
            
            categoryDeals.add(deal);
            dealCategory.setDealstbCollection(categoryDeals);
            em.merge(dealCategory);
        }
        em.merge(deal);
    }

    @Override
    public void removeDeal(int DealID) {
        Dealstb deal = em.find(Dealstb.class, DealID);
        Dealscategorytb oldCategory = em.find(Dealscategorytb.class, deal.getDealsCategoryID().getCategoryID());
        Collection<Dealstb> oldCategoryDeals = oldCategory.getDealstbCollection();
        oldCategoryDeals.remove(deal);
        oldCategory.setDealstbCollection(oldCategoryDeals);
        em.merge(oldCategory);
        em.remove(deal);
    }

    @Override
    public Collection<Dealstb> getBusinessDeals(int BusinessID) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        return business.getDealstbCollection();
    }

    @Override
    public Collection<Dealspaymenttb> getDealPayments(int DealID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ChangeDealStatus(int DealDetailID, int Status, String UseDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDealDetails(int DealID, Boolean CancellationAllowed, String HowToUse, String ThingsToRemember, String Inclusion, int ValidFor, String ValidOn) {
        Dealstb deal = em.find(Dealstb.class, DealID);
        Collection<Dealsdetailstb> details = deal.getDealsdetailstbCollection();
        
        Dealsdetailstb detail = new Dealsdetailstb();
        detail.setCanncellationAllowed(CancellationAllowed);
        detail.setHowToUse(HowToUse);
        detail.setThingsToRemember(ThingsToRemember);
        detail.setInclusion(Inclusion);
        detail.setValidFor(ValidFor);
        detail.setValidOn(ValidOn);
        detail.setDealID(deal);
        details.add(detail);
        deal.setDealsdetailstbCollection(details);
        em.persist(detail);
        em.merge(deal);
    }

    @Override
    public void editDealDetails(int DealDetailsID, Boolean CancellationAllowed, String HowToUse, String ThingsToRemember, String Inclusion, int ValidFor, String ValidOn) {
        Dealsdetailstb details = em.find(Dealsdetailstb.class, DealDetailsID);
        details.setCanncellationAllowed(CancellationAllowed);
        details.setHowToUse(HowToUse);
        details.setThingsToRemember(ThingsToRemember);
        details.setInclusion(Inclusion);
        details.setValidFor(ValidFor);
        details.setValidOn(ValidOn);
        em.persist(details);
    }

    @Override
    public void removeDealDetails(int DealDetailsID) {
        Dealsdetailstb detail = em.find(Dealsdetailstb.class, DealDetailsID);
        Dealstb deal = detail.getDealID();
        Collection<Dealsdetailstb> details = deal.getDealsdetailstbCollection();
        details.remove(detail);
        deal.setDealsdetailstbCollection(details);
        em.merge(deal);
        em.remove(detail);
    }

    @Override
    public void addDealMenu(int DealID, String MenuData) {
        Dealstb deal = em.find(Dealstb.class, DealID);
        Collection<Dealsmenutb> menus = deal.getDealsmenutbCollection();
        
        Dealsmenutb menu = new Dealsmenutb();
        menu.setMenuData(MenuData);
        menu.setDealID(deal);
        menus.add(menu);
        deal.setDealsmenutbCollection(menus);
        em.persist(menu);
        em.merge(deal);
    }

    @Override
    public void editDealMenu(int MenuID, String MenuData) {
        Dealsmenutb menu = em.find(Dealsmenutb.class, MenuID);
        menu.setMenuData(MenuData);
        em.merge(menu);
    }

    @Override
    public void removeDealMenu(int MenuID) {
        Dealsmenutb menu = em.find(Dealsmenutb.class, MenuID);
        Dealstb deal = menu.getDealID();
        Collection<Dealsmenutb> menus = deal.getDealsmenutbCollection();
        menus.remove(menu);
        deal.setDealsmenutbCollection(menus);
        em.merge(deal);
        em.remove(menu);
    }

    @Override
    public void changeUsageStatus(int DealUsageID) {
        Dealsusagetb dUsage = em.find(Dealsusagetb.class, DealUsageID);
        dUsage.setStatus(2);
        dUsage.setUsageDate(new Date());
        em.persist(dUsage);
    }
}
