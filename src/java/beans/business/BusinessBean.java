/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Businesscategorytb;
import entity.Businesslinkstb;
import entity.Businessphotostb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Informationtb;
import entity.Linkstb;
import entity.Statetb;
import entity.Usertb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import static jwtrest.Constants.FILE_UPLOAD;
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
    BusinessClient businessClient;
    Response res;
    
    private Part uploadedFile;
    private String image;
    private String folder = FILE_UPLOAD+"business";
    
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
    GenericType<Collection<Businessphotostb>> gBusinessPhotos;
    Collection<Businessphotostb> photos;
    GenericType<Collection<Informationtb>> gAllInfos;
    Collection<Informationtb> allInfos;
    GenericType<Collection<Informationtb>> gBuInfos;
    Collection<Informationtb> buInfos;
    private int[] selectedInfos;

    GenericType<Collection<Linkstb>> gAllLinks;
    Collection<Linkstb> allLinks;
    GenericType<Collection<Businesslinkstb>> gBuLinks;
    Collection<Businesslinkstb> buLinks;
    
    private int linkId;
    private String linkAddress;
    
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

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public Collection<Businesslinkstb> getBuLinks() {
        return buLinks;
    }

    public Collection<Linkstb> getAllLinks() {
        return allLinks;
    }

    public int[] getSelectedInfos() {
        return selectedInfos;
    }

    public void setSelectedInfos(int[] selectedInfos) {
        this.selectedInfos = selectedInfos;
    }

    public Collection<Informationtb> getAllInfos() {
        return allInfos;
    }

    public Collection<Informationtb> getBuInfos() {
        return buInfos;
    }

    public Collection<Businessphotostb> getPhotos() {
        return photos;
    }

    public void testChanged() {
        for (int selectedInfo : selectedInfos) {
            System.out.print(selectedInfo);
        }
    }
    
    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
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
    
    // Register
    public void stateChanged() {
        res = commonClient.getCitiesByState(Response.class, String.valueOf(selectedState));
        cities = res.readEntity(gCities);
    }
    public void categoryChanged() {
        res = commonClient.getBussinessTypesByCategory(Response.class, String.valueOf(selectedBusinessCategory));
        btypes = res.readEntity(gBTypes);
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
        //Usertb user = this.registerAndGetUser();
        
        Businesstb bins = new Businesstb();
        bins.setAddress(Address);
        bins.setBusinessName(BusinessName);
        bins.setEmailID(BusinessEmailID);
        //bins.setUserID(user);
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
        
        System.out.println(selectedState+" "+selectedCity+" "+selectedBusinessCategory+" "+selectedBusinessType);
        //commonClient.registerBusiness(bins);
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
    // End of Register
    
    // Home
    public void getAndSetBusinessDetails() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession h = request.getSession();
        if(h.getAttribute("businessid") == null) {
            String email = h.getAttribute("useremail").toString();
            System.out.println("setting bussiness session logged user email "+email);
            res = commonClient.getBusinessByUserEmail(Response.class, email);
            business = res.readEntity(gBusiness);
            h.setAttribute("businessid", business.getBusinessID());   
        }
    }
    // End of Home
    
    // Photos
    public void getBusinessPhotos() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessId = request.getSession().getAttribute("businessid").toString();
        res = commonClient.getBusinessPhotos(Response.class, businessId);
        photos = res.readEntity(gBusinessPhotos);
    }
    public String uploadBusinessPhoto() {
        if(uploadedFile != null) {
            this.saveFile();
            Collection<String> photos = new ArrayList<>();
            photos.add(image);
            
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String token = request.getSession().getAttribute("token").toString();
            String businessId = request.getSession().getAttribute("businessid").toString();
            businessClient = new BusinessClient(token);
            businessClient.setBusinessPhotos(photos, businessId);
        }
        return "/business/photos.jsf?faces-redirect=true";
    }
    public String removeBusinessPhoto(int BusinessPhotoId) {
        System.out.println("Remove Business Photo for "+BusinessPhotoId);
        businessClient.removeBusinessPhoto(String.valueOf(BusinessPhotoId));
        return "/business/photos.jsf?faces-redirect=true";
    }
    public void saveFile() {
        try (InputStream input = uploadedFile.getInputStream()) {
            image = uploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, image).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // End of Photos
    
    // Details
    public void getBusinessDetails() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        res = commonClient.getBusiness(Response.class, businessid);
        business = res.readEntity(gBusiness);
    }
    public String editBusiness() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        businessClient = new BusinessClient(token);
        businessClient.editBusiness(business);
        return "/business/details.jsf?faces-redirect=true";
    }
    // End of Details
    
    // Facilities
    public void getAllAndBusinessInfos() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        
        res = commonClient.getBusinessInfoList(Response.class);
        allInfos = res.readEntity(gAllInfos);
        res = commonClient.getBusinessInfos(Response.class, businessid);
        buInfos = res.readEntity(gBuInfos);
    }
    public String updateBusinessInformations() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        String token = request.getSession().getAttribute("token").toString();
        businessClient = new BusinessClient(token);
        businessClient.setBusinessInfo(selectedInfos, businessid);
        return "/business/facilities.jsf?faces-redirect=true";
    }
    // End of Facilities
    
    // Links
    public void getAllAndBusinessLinks() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        res = commonClient.getLinks(Response.class);
        allLinks = res.readEntity(gAllLinks);
        res = commonClient.getBusinessLinks(Response.class, businessid);
        buLinks = res.readEntity(gBuLinks);
    }
    public String submitLink() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        String token = request.getSession().getAttribute("token").toString();
        businessClient = new BusinessClient(token);
        //System.out.println(businessid+" "+String.valueOf(linkId)+" "+linkAddress);
        //businessClient.setBusinessLinks(businessid,  String.valueOf(linkId), linkAddress);
        businessClient.setBusinessLinks(linkAddress, businessid, String.valueOf(linkId));
        //businessClient.setBusinessLinks(Response.class, businessid, String.valueOf(linkId), linkAddress);
        return "/business/links.jsf?faces-redirect=true";
    }
    // End of Links
    
    public BusinessBean() {
        gBCategories = new GenericType<Collection<Businesscategorytb>>(){};
        gBTypes = new GenericType<Collection<Businesstypetb>>(){};
        gStates = new GenericType<Collection<Statetb>>(){};
        gCities = new GenericType<Collection<Citytb>>(){};
        gBusiness = new GenericType<Businesstb>(){};
        gBusinessPhotos = new GenericType<Collection<Businessphotostb>>(){};
        gAllInfos = new GenericType<Collection<Informationtb>>(){};
        gBuInfos = new GenericType<Collection<Informationtb>>(){};
        gAllLinks = new GenericType<Collection<Linkstb>>(){};
        gBuLinks = new GenericType<Collection<Businesslinkstb>>(){};
        
        pbkd = new Pbkdf2PasswordHashImpl();
        commonClient = new CommonClient();
        res = commonClient.getBusinessCategory(Response.class);
        bcategories = res.readEntity(gBCategories);
        res = commonClient.getAllStates(Response.class);
        states = res.readEntity(gStates);
    }
    
}
