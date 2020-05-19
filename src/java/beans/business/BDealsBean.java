/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Dealscategorytb;
import entity.Dealsdetailstb;
import entity.Dealstb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import static jwtrest.Constants.FILE_UPLOAD;

/**
 *
 * @author bhavik
 */
@Named(value = "bDealsBean")
@SessionScoped
public class BDealsBean implements Serializable {

    CommonClient commonClient;
    BusinessClient businessClient;
    Response res;
    
//    private UploadedFile file;
    private Part UploadedFile;
    private String image;
    private String imageError;
    private String folder = FILE_UPLOAD+"deals";
    private String BusinessId;
    
    GenericType<Collection<Dealscategorytb>> gdcategories;
    Collection<Dealscategorytb> dcategories;
    GenericType<Collection<Dealstb>> gdeals;
    Collection<Dealstb> deals;
    GenericType<Dealsdetailstb> gdealdetail;
    Dealsdetailstb dealdetail;
    
    private String messageType;
    private String message;
    
    private String DealName;
    private Date IssueDate;
    private Date DueDate;
    private int AverageCost;
    private int DealsCategory;
    
    private int SelectedDealId;
    private String HowToUse;
    private String ThingsToRemember;
    private int ValidFor;
    private int SelectedDealCategory;
    private String selectedDays;
    private int DealDetailsId;

    public String getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(String selectedDays) {
        this.selectedDays = selectedDays;
    }
   
    public int getSelectedDealCategory() {
        return SelectedDealCategory;
    }

    public void setSelectedDealCategory(int SelectedDealCategory) {
        this.SelectedDealCategory = SelectedDealCategory;
    }

    public String getHowToUse() {
        return HowToUse;
    }

    public void setHowToUse(String HowToUse) {
        this.HowToUse = HowToUse;
    }

    public String getThingsToRemember() {
        return ThingsToRemember;
    }

    public void setThingsToRemember(String ThingsToRemember) {
        this.ThingsToRemember = ThingsToRemember;
    }

    public int getValidFor() {
        return ValidFor;
    }

    public void setValidFor(int ValidFor) {
        this.ValidFor = ValidFor;
    }

    public Collection<Dealstb> getDeals() {
        return deals;
    }

    public int getSelectedDealId() {
        return SelectedDealId;
    }

    public void setSelectedDealId(int SelectedDealId) {
        this.SelectedDealId = SelectedDealId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    public Part getUploadedFile() {
        return UploadedFile;
    }

    public void setUploadedFile(Part UploadedFile) {
        this.UploadedFile = UploadedFile;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
    
    public String getDealName() {
        return DealName;
    }

    public void setDealName(String DealName) {
        this.DealName = DealName;
    }

    public int getAverageCost() {
        return AverageCost;
    }

    public void setAverageCost(int AverageCost) {
        this.AverageCost = AverageCost;
    }

    public int getDealsCategory() {
        return DealsCategory;
    }

    public void setDealsCategory(int DealsCategory) {
        this.DealsCategory = DealsCategory;
    }

    public Collection<Dealscategorytb> getDcategories() {
        return dcategories;
    }
    
    //Add Deal
    public void fetchDealsData() {
        res = commonClient.getDealsCategory(Response.class);
        dcategories = res.readEntity(gdcategories);
    }
    public String addDeal() throws IOException {
        this.saveImage();
        Dealscategorytb category = new Dealscategorytb();
        category.setCategoryID(DealsCategory);
        
        Dealstb deal = new Dealstb();
        deal.setName(DealName);
        deal.setAverageCost(AverageCost);
        deal.setBannerImage(image);
        deal.setIssueDate(IssueDate);
        deal.setDueDate(DueDate);
        deal.setDealsCategoryID(category);
        businessClient.addBusinessDeal(deal, BusinessId);

        messageType = "success";
        message = "Deal created Successfully";
        
        return "/business/adddeal.jsf";
    }
    public void saveImage() {
        try (InputStream input = UploadedFile.getInputStream()) {
            image = UploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, image).toPath());
            System.out.println("file uploaded");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    //End of Add Deal
    
    //Deal Details
    public void fetchBuDeals() {
        res = businessClient.getAllDeals(Response.class, BusinessId);
        deals = res.readEntity(gdeals);
    }
    public String editDetails() {
        return "/business/dealdetails.jsf";
    }
    public void dealChanged() {
        res = commonClient.getDealDetails(Response.class, String.valueOf(SelectedDealId));
        dealdetail = res.readEntity(gdealdetail);
        HowToUse = dealdetail.getHowToUse();
        ThingsToRemember = dealdetail.getThingsToRemember();
        ValidFor = dealdetail.getValidFor();
        selectedDays = dealdetail.getValidOn();
        DealDetailsId = dealdetail.getDealDetailID();
    }
    public String submitDealDetails() {
        Dealsdetailstb detail = new Dealsdetailstb();
        detail.setCanncellationAllowed(true);
        detail.setHowToUse(HowToUse);
        detail.setInclusion(image);
        if(DealDetailsId==0) {
            
        }
        return "/business/dealdetails.jsf?faces-redierct=true";
    }
    //End of Deal Details
    
    public void resetAlert() {
        message = "";
        messageType = "";
    }
    
    public BDealsBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        BusinessId = request.getSession().getAttribute("businessid").toString();
        businessClient = new BusinessClient(token);
        commonClient = new CommonClient();
        gdcategories = new GenericType<Collection<Dealscategorytb>>(){};
        gdeals = new GenericType<Collection<Dealstb>>(){};
        gdealdetail = new GenericType<Dealsdetailstb>(){};
    }
    
}
