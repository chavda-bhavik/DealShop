/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.CommonClient;
import entity.Businesscategorytb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Statetb;
import entity.Usertb;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author bhavik
 */
@Named(value = "businessBean")
@SessionScoped
public class BusinessBean implements Serializable {

    Pbkdf2PasswordHashImpl pbkd;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Businesscategorytb>> gBCategories;
    Collection<Businesscategorytb> bcategories;
    GenericType<Collection<Businesstypetb>> gBTypes;
    Collection<Businesstypetb> btypes;
    GenericType<Collection<Statetb>> gStates;
    Collection<Statetb> states;
    GenericType<Collection<Citytb>> gCities;
    Collection<Citytb> cities;
    GenericType<Businesstb> gBusiness;
    Businesstb business;
    
    private String UserName;
    private String UserEmail;
    private String Userpassword;
    private String ConfirmUserPassword;
    private String passwordError;
    
    private int selectedState;
    private int selectedCity;
    private int selectedBusinessCategory;
    private int selectedBusinessType;
    private String BusinessName;
    private String BusinessEmailID;
    private String Address;
    private String CustomerCareNumber;
    private String ReservationNumber;
    private String DaysOfOperation;
    private String HoursOfOperation;
    private String AwardsRecognition;
    private int status;
    private int BusinessCategory;
    private int BusinessType;
    private int State;
    private int City;
    
    public void stateChanged() {
        res = commonClient.getCitiesByState(Response.class, String.valueOf(selectedState));
        cities = res.readEntity(gCities);
    }
    
    public void categoryChanged() {
        res = commonClient.getBussinessTypesByCategory(Response.class, String.valueOf(selectedBusinessCategory));
        btypes = res.readEntity(gBTypes);
    }
    
    public int getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(int selectedState) {
        this.selectedState = selectedState;
    }

    public int getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(int selectedCity) {
        this.selectedCity = selectedCity;
    }

    public int getSelectedBusinessCategory() {
        return selectedBusinessCategory;
    }

    public void setSelectedBusinessCategory(int selectedBusinessCategory) {
        this.selectedBusinessCategory = selectedBusinessCategory;
    }

    public int getSelectedBusinessType() {
        return selectedBusinessType;
    }

    public void setSelectedBusinessType(int selectedBusinessType) {
        System.out.println("btype set to" + selectedBusinessType);
        this.selectedBusinessType = selectedBusinessType;
    }

    public void confirmPassword() {
        System.out.println("Confirm Password called");
        try{
            if(this.getConfirmUserPassword() == "" || ConfirmUserPassword == null) {
                this.setPasswordError("Confirm Password is required");
            } else if(!ConfirmUserPassword.equals(this.getUserpassword())) {
                this.setPasswordError("Password doesn't match");
            } else {
                this.setPasswordError("");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmUserPassword() {
        return ConfirmUserPassword;
    }

    public void setConfirmUserPassword(String ConfirmUserPassword) {
        this.ConfirmUserPassword = ConfirmUserPassword;
    }

    public Collection<Businesscategorytb> getBcategories() {
        return bcategories;
    }

    public void setBcategories(Collection<Businesscategorytb> bcategories) {
        this.bcategories = bcategories;
    }

    public Collection<Businesstypetb> getBtypes() {
        return btypes;
    }

    public void setBtypes(Collection<Businesstypetb> btypes) {
        this.btypes = btypes;
    }

    public Collection<Statetb> getStates() {
        return states;
    }

    public void setStates(Collection<Statetb> states) {
        this.states = states;
    }

    public Collection<Citytb> getCities() {
        return cities;
    }

    public void setCities(Collection<Citytb> cities) {
        this.cities = cities;
    }

    public Businesstb getBusiness() {
        return business;
    }

    public void setBusiness(Businesstb business) {
        this.business = business;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    public void setUserpassword(String Userpassword) {
        this.Userpassword = Userpassword;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getBusinessEmailID() {
        return BusinessEmailID;
    }

    public void setBusinessEmailID(String BusinessEmailID) {
        this.BusinessEmailID = BusinessEmailID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCustomerCareNumber() {
        return CustomerCareNumber;
    }

    public void setCustomerCareNumber(String CustomerCareNumber) {
        this.CustomerCareNumber = CustomerCareNumber;
    }

    public String getReservationNumber() {
        return ReservationNumber;
    }

    public void setReservationNumber(String ReservationNumber) {
        this.ReservationNumber = ReservationNumber;
    }

    public String getDaysOfOperation() {
        return DaysOfOperation;
    }

    public void setDaysOfOperation(String DaysOfOperation) {
        this.DaysOfOperation = DaysOfOperation;
    }

    public String getHoursOfOperation() {
        return HoursOfOperation;
    }

    public void setHoursOfOperation(String HoursOfOperation) {
        this.HoursOfOperation = HoursOfOperation;
    }

    public String getAwardsRecognition() {
        return AwardsRecognition;
    }

    public void setAwardsRecognition(String AwardsRecognition) {
        this.AwardsRecognition = AwardsRecognition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBusinessCategory() {
        return BusinessCategory;
    }

    public void setBusinessCategory(int BusinessCategory) {
        this.BusinessCategory = BusinessCategory;
    }

    public int getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(int BusinessType) {
        this.BusinessType = BusinessType;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public int getCity() {
        return City;
    }

    public void setCity(int City) {
        this.City = City;
    }
    
    public String goToRegisterBusiness() {
        return "/business/Register.jsf";
    }
    
    public String RegisterBusiness() {
        Statetb state = new Statetb();
        state.setStateID(selectedState);
        Citytb city = new Citytb();
        city.setCityID(selectedCity);
        Businesscategorytb bcateg = new Businesscategorytb();
        bcateg.setCategoryID(selectedBusinessCategory);
        Businesstypetb btyp = new Businesstypetb();
        btyp.setBusinessTypeID(selectedBusinessType);
        Usertb user = this.registerAndGetUser();
        
        Businesstb bins = new Businesstb();
        bins.setAddress(Address);
        bins.setBusinessName(BusinessName);
        bins.setEmailID(BusinessEmailID);
        bins.setUserID(user);
        bins.setStateID(state);
        bins.setCityID(city);
        bins.setBusinessCategoryID(bcateg);
        bins.setBusinessTypeID(btyp);
        bins.setCustomerCarePhoneNo("");
        bins.setReservationPhoneNo("");
        bins.setNeedToKnow("");
        bins.setHoursOfOperation("");
        bins.setLocation("");
        bins.setAwardsRecognition("");
        bins.setDaysOfOperation("");
        
        commonClient.registerBusiness(bins);
        return "/business/Register.jsf?faces-redirect=true";
    }
    
    public Usertb registerAndGetUser() {
        Usertb user = new Usertb();
        user.setEmail(UserEmail);
        user.setName(UserName);
        user.setPassword(pbkd.generate(Userpassword.toCharArray()));
        commonClient.registerBusinessUser(user);
        
        res = commonClient.getLoginUser(Response.class, UserEmail);
        GenericType<Usertb> gUser = new GenericType<Usertb>(){};
        user = res.readEntity(gUser);
        return user;
    }
    
    public String EditBusiness() {
        return "/business/profile.jsf?faces-redirect=true";
    }
    
    public BusinessBean() {
        gBCategories = new GenericType<Collection<Businesscategorytb>>(){};
        gBTypes = new GenericType<Collection<Businesstypetb>>(){};
        gStates = new GenericType<Collection<Statetb>>(){};
        gCities = new GenericType<Collection<Citytb>>(){};
        
        pbkd = new Pbkdf2PasswordHashImpl();
        commonClient = new CommonClient();
        res = commonClient.getBusinessCategory(Response.class);
        bcategories = res.readEntity(gBCategories);
        res = commonClient.getAllStates(Response.class);
        states = res.readEntity(gStates);
    }
    
}
