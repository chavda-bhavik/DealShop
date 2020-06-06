/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.AdminDashboard;
import entity.Businesscategorytb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Dealscategorytb;
import entity.Dealstb;
import entity.Informationtb;
import entity.Linkstb;
import entity.Offertb;
import entity.Redeems;
import entity.Statetb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author bhavik
 */
@Stateless
public class AdminBean implements AdminBeanLocal {
    @PersistenceContext(unitName="DealShopPU")
    EntityManager em;
    
    @Override
    public void addBussinessCategory(String CategoryName, String Image) {
        Businesscategorytb bcategory = new Businesscategorytb();
        bcategory.setName(CategoryName);
        bcategory.setImage(Image);
        em.persist(bcategory);
    }

    @Override
    public void editBussinessCategory(int CategoryID, String CategoryName, String Image) {
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        bcategory.setName(CategoryName);
        bcategory.setImage(Image);
        em.merge(bcategory);
    }

    @Override
    public void removeBussinessCategory(int CategoryID) {
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        em.remove(bcategory);
    }

    @Override
    public void addBussinessType(String TypeName, int CategoryID, String Image) {
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstypetb> btypes = bcategory.getBusinesstypetbCollection();
        
        Businesstypetb btype = new Businesstypetb();
        btype.setName(TypeName);
        btype.setImage(Image);
        btype.setBusinessCategoryID(bcategory);
        btypes.add(btype);
        
        em.persist(btype);
        em.merge(bcategory);
    }

    @Override
    public void editBussinessType(int TypeID, String TypeName, int CategoryID, String Image) {
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstypetb> btypes = bcategory.getBusinesstypetbCollection();
        
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);
        btype.setName(TypeName);
        btype.setImage(Image);
        btype.setBusinessCategoryID(bcategory);
        btypes.add(btype);
        
        em.persist(btype);
        em.merge(bcategory);
    }

    @Override
    public void removeBussinessType(int TypeID) {
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);        
        em.remove(btype);
    }

    @Override
    public void addInformation(String InfoName) {
        Informationtb info = new Informationtb();
        info.setTitle(InfoName);
        em.persist(info);
    }

    @Override
    public void editInformation(int InfoID, String InfoName) {
        Informationtb info = em.find(Informationtb.class, InfoID);
        info.setTitle(InfoName);
        em.merge(info);
    }

    @Override
    public void removeInformation(int InfoID) {
        Informationtb info = em.find(Informationtb.class, InfoID);
        em.remove(info);
    }

    @Override
    public void addLink(String Name, String Logo) {
        Linkstb link = new Linkstb();
        link.setName(Name);
        link.setLogo(Logo);
        em.persist(link);
    }

    @Override
    public void editLink(int LinkID, String Name, String Logo) {
        Linkstb link = em.find(Linkstb.class, LinkID);
        link.setLogo(Logo);
        link.setName(Name);
        em.merge(link);
    }

    @Override
    public void removeLink(int LinkID) {
        Linkstb link = em.find(Linkstb.class, LinkID);
        em.remove(link);
    }

    @Override
    public void removeBussiness(int BussinessID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void blockBussiness(int BussinessID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verifyBusinessSuccess(int BussinessID) {
        Businesstb business = em.find(Businesstb.class, BussinessID);
        business.setIsVerified(2);
        em.merge(business);
    }

    @Override
    public void verifyDealSuccess(int DealID) {
        Dealstb deal = em.find(Dealstb.class, DealID);
        deal.setIsVerified(2);
        em.merge(deal);
    }

    @Override
    public void addState(String Name) {
        Statetb state = new Statetb();
        state.setName(Name);
        em.persist(state);
    }

    @Override
    public void editState(int StateID, String Name) {
        Statetb state = em.find(Statetb.class, StateID);
        state.setName(Name);
        em.merge(state);
    }

    @Override
    public void removeState(int StateID) {
        Statetb state = em.find(Statetb.class, StateID);
        if(state!=null) {
            em.remove(state);
        }
    }

    @Override
    public void addCity(String Name, int StateID) {
        Statetb state = em.find(Statetb.class, StateID);
        
//        int total = Integer.parseInt(em.createNamedQuery("Citytb.countByName").setParameter("name", Name).getParameter("total").toString());
//        System.out.println(total);
//        if(total == 0) {
            Collection<Citytb> stateCities = state.getCitytbCollection();
            Citytb city = new Citytb();
            city.setName(Name);
            city.setStateID(state);
            stateCities.add(city);
            em.merge(state);
            em.persist(city);
//        }
    }

    @Override
    public void editCity(int CityID, String Name, int StateID) {
        Citytb city = em.find(Citytb.class, CityID);
        city.setName(Name);
        if(city.getStateID().getStateID() != StateID) {
            Statetb state = em.find(Statetb.class, StateID);
            city.setStateID(state);
            Collection<Citytb> stateCities = state.getCitytbCollection();
            stateCities.add(city);
            state.setCitytbCollection(stateCities);
            em.merge(state);
        }
        em.merge(city);
    }

    @Override
    public void removeCity(int CityID) {
        Citytb city = em.find(Citytb.class, CityID);
        em.remove(city);
    }

    @Override
    public void addDealsCategory(String Name, String Image) {
        Dealscategorytb dcategory = new Dealscategorytb();
        dcategory.setName(Name);
        dcategory.setImage(Image);
        em.persist(dcategory);
    }

    @Override
    public void editDealsCategory(int CategoryID, String Name, String Image) {
        Dealscategorytb dcategory = em.find(Dealscategorytb.class, CategoryID);
        dcategory.setName(Name);
        dcategory.setImage(Image);
        em.merge(dcategory);
    }

    @Override
    public void removeDealsCategory(int CategoryID) {
        Dealscategorytb dcategory = em.find(Dealscategorytb.class, CategoryID);
        em.remove(dcategory);
    }

    @Override
    public void changeDealStatus(int DealID, int Status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOffer(String Name, String Code, String TermsConditions, Date IssueDate, Date DueDate, String BannerImage, String OfferImage, int PercentOff, int DollarsOff) {
        Offertb offer = new Offertb();
        offer.setName(Name);
        offer.setCode(Code);
        offer.setTermsConditions(TermsConditions);
        offer.setPercentOff(PercentOff);
        offer.setDollarsOff(DollarsOff);
        offer.setDueDate(DueDate);
        offer.setIssueDate(IssueDate);
        offer.setOfferImage(OfferImage);
        offer.setBannerImage(BannerImage);
        em.persist(offer);
    }

    @Override
    public void editOffer(int OfferID, String Name, String Code, String TermsConditions, Date IssueDate, Date DueDate, String BannerImage, String OfferImage, int PercentOff, int DollarsOff) {
        Offertb offer = em.find(Offertb.class, OfferID);
        offer.setName(Name);
        offer.setCode(Code);
        offer.setPercentOff(PercentOff);
        offer.setDollarsOff(DollarsOff);
        offer.setTermsConditions(TermsConditions);        
        offer.setDueDate(DueDate);
        offer.setIssueDate(IssueDate);
        offer.setOfferImage(OfferImage);
        offer.setBannerImage(BannerImage);
        em.merge(offer);
    }

    @Override
    public void removeOffer(int OfferID) {
        Offertb offer = em.find(Offertb.class, OfferID);
        em.remove(offer);
    }
    
    // Dashboard
    @Override
    public AdminDashboard getDashboardData() {
        int totalBusi = em.createNamedQuery("Businesstb.findAll").getResultList().size();
        int totalVerifiedBusi = em.createNamedQuery("Businesstb.findByIsVerified").setParameter("isVerified", 2).getResultList().size();
        int totalLinks = em.createNamedQuery("Linkstb.findAll").getResultList().size();
        int totalInfos = em.createNamedQuery("Informationtb.findAll").getResultList().size();
        int totalCities = em.createNamedQuery("Citytb.findAll").getResultList().size();
        int totalStates = em.createNamedQuery("Statetb.findAll").getResultList().size();
        int totalOffers = em.createNamedQuery("Offertb.findAll").getResultList().size();
        int totalBusiCategories = em.createNamedQuery("Businesscategorytb.findAll").getResultList().size();
        int totalBusiTypes = em.createNamedQuery("Businesstypetb.findAll").getResultList().size();
        int totalDealCategories = em.createNamedQuery("Dealscategorytb.findAll").getResultList().size();
        int totalReviews = em.createNamedQuery("Reviewtb.findAll").getResultList().size();
        int totalPayments = em.createNamedQuery("Dealspaymenttb.findAll").getResultList().size();
        int totalDeals = em.createNamedQuery("Dealstb.findAll").getResultList().size();
        int totalUsers = em.createNamedQuery("Usertb.findAll").getResultList().size();
        
        Date today = new Date();
        Long totalActiveDeals = (Long) em.createQuery("SELECT COUNT(d) FROM Dealstb d WHERE d.isVerified = 2 AND d.dueDate < :today").setParameter("today",today,TemporalType.DATE).getSingleResult();
        
        int totalDealsSold = em.createNamedQuery("Dealsusagetb.findAll").getResultList().size();
        Long totalMoneyEarned = (Long) em.createQuery("SELECT SUM(d.dealID.averageCost) AS total FROM Dealsusagetb d").getSingleResult();
        Long totalMoneyRedeemed = (Long) em.createQuery("SELECT SUM(d.dealID.averageCost) AS total FROM Dealsusagetb d WHERE d.status = 3").getSingleResult();
        if(totalMoneyRedeemed == null) {
            totalMoneyRedeemed = Long.valueOf(0);
        }
        
        AdminDashboard ad = new AdminDashboard();
        ad.setTotalUsers(totalUsers);
        ad.setTotalActiveDeals(totalActiveDeals.intValue());
        ad.setTotalDeals(totalDeals);
        ad.setTotalBusinesses(totalBusi);
        ad.setTotalVerifiedBusinesses(totalVerifiedBusi);
        ad.setTotalLinks(totalLinks);
        ad.setTotalInfos(totalInfos);
        ad.setTotalCities(totalCities);
        ad.setTotalStates(totalStates);
        ad.setTotalOffers(totalOffers);
        ad.setTotalBusiCategories(totalBusiCategories);
        ad.setTotalBusiTypes(totalBusiTypes);
        ad.setTotalDealsCategories(totalDealCategories);
        ad.setTotalReviews(totalReviews);
        ad.setTotalPayments(totalPayments);
        ad.setTotalDealsSold(totalDealsSold);
        ad.setTotalEarned(totalMoneyEarned.intValue());
        ad.setTotalRedeemed(totalMoneyRedeemed.intValue());
        
        return ad;
    }

    @Override
    public Collection<Redeems> getRedeems() {
        Query q = em.createQuery("SELECT bu.businessID, bu.businessName, bu.bankAccountNo, bu.iFSCCode, bu.bankName, bu.reservationPhoneNo, bu.customerCarePhoneNo, bu.emailID, SUM(du.dealID.averageCost) as RedeemAmount FROM Dealsusagetb du, Businesstb bu WHERE du.dealID.businessID.businessID = bu.businessID GROUP BY bu.businessID");
        Collection<Object[]> objs = q.getResultList();
        Collection<Redeems> rdms = new ArrayList<>();
        for (Object[] obj : objs) {
            rdms.add(new Redeems(obj));
        }
        return rdms;
    }
}
