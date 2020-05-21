/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import client.BusinessClient;
import client.CommonClient;
import entity.Businessphotostb;
import entity.Dealphotostb;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import static jwtrest.Constants.FILE_UPLOAD;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

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
    
    private UploadedFile file;
    private UploadedFiles files;
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
    GenericType<Collection<Dealphotostb>> gdealphotos;
    Collection<Dealphotostb> dealphotos;
    GenericType<Dealstb> gdeal;
    
    private String messageType;
    private String message;
    
    private String DealName;
    private Date IssueDate;
    private Date DueDate;
    private int AverageCost;
    private int DealsCategory;
    private int DealFormId;
    
    private int SelectedDealId;
    private String HowToUse;
    private String ThingsToRemember;
    private String Inclusion;
    private Boolean CancellationAllowed;
    private int ValidFor;
    private int SelectedDealCategory;
    private String selectedDays;
    private int DealDetailsId;
    
    private int DealsShowType;
    private Collection<Dealstb> ShowDealsList;

    public String getImage() {
        return image;
    }

    public int getDealFormId() {
        return DealFormId;
    }

    public void setDealFormId(int DealFormId) {
        this.DealFormId = DealFormId;
    }

    public Collection<Dealstb> getShowDealsList() {
        return ShowDealsList;
    }

    public void setShowDealsList(Collection<Dealstb> ShowDealsList) {
        this.ShowDealsList = ShowDealsList;
    }

    public int getDealsShowType() {
        return DealsShowType;
    }

    public void setDealsShowType(int DealsShowType) {
        this.DealsShowType = DealsShowType;
    }

    public Collection<Dealphotostb> getDealphotos() {
        return dealphotos;
    }

    public void setDealphotos(Collection<Dealphotostb> dealphotos) {
        this.dealphotos = dealphotos;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public Boolean getCancellationAllowed() {
        return CancellationAllowed;
    }

    public void setCancellationAllowed(Boolean CancellationAllowed) {
        this.CancellationAllowed = CancellationAllowed;
    }

    public String getInclusion() {
        return Inclusion;
    }

    public void setInclusion(String Inclusion) {
        this.Inclusion = Inclusion;
    }

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
    public String gotoAddDeal() {
        DealName = null;
        IssueDate = null;
        DueDate = null;
        AverageCost = 0;
        DealsCategory = 0;
        image = null;
        DealFormId = 0;
        return "/business/adddeal.jsf/faces-redirect=true";
    }
    public void fetchDealsData() {
        res = commonClient.getDealsCategory(Response.class);
        dcategories = res.readEntity(gdcategories);
    }
    public String addDeal() throws IOException {
        if(UploadedFile != null) {
            this.saveImage();   
        }
        Dealscategorytb category = new Dealscategorytb();
        category.setCategoryID(DealsCategory);
        
        Dealstb deal = new Dealstb();
        deal.setName(DealName);
        deal.setAverageCost(AverageCost);
        deal.setBannerImage(image);
        deal.setIssueDate(IssueDate);
        deal.setDueDate(DueDate);
        deal.setDealsCategoryID(category);
        if(DealFormId != 0) {
            deal.setDealID(DealFormId);
            businessClient.editDeal(deal);
            messageType = "success";
            message = "Deal edited Successfully";
        } else {
            businessClient.addBusinessDeal(deal, BusinessId);
            messageType = "success";
            message = "Deal created Successfully";
        }
        
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
        System.out.println("deals fetched " + deals.size());
        ShowDealsList = deals;
        DealsShowType = 0;
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
        Inclusion = dealdetail.getInclusion();
        CancellationAllowed = dealdetail.getCanncellationAllowed();
        DealDetailsId = dealdetail.getDealDetailID();
    }
    public String submitDealDetails() {
        Dealsdetailstb detail = new Dealsdetailstb();
        detail.setHowToUse(HowToUse);
        detail.setInclusion(image);
        detail.setThingsToRemember(ThingsToRemember);
        detail.setCanncellationAllowed(CancellationAllowed);
        detail.setInclusion(Inclusion);
        detail.setValidFor(ValidFor);
        detail.setValidOn(selectedDays);
        if(DealDetailsId==0) {
            businessClient.addDealDetails(detail, String.valueOf(SelectedDealId));
        } else {
            businessClient.editDealDetails(detail, String.valueOf(DealDetailsId));
        }
        return "/business/dealdetails.jsf?faces-redierct=true";
    }
    //End of Deal Details
    
    // Deal Photos
    public void photoDealChanged() {
        res = commonClient.getDealPhotos(Response.class, String.valueOf(SelectedDealId));
        dealphotos = res.readEntity(gdealphotos);
    }
    public String submitDealPhotos() {
        Collection<String> images = new ArrayList<>();
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
                try (InputStream input = f.getInputStream()) {
                    images.add(f.getFileName());
                    Files.copy(input, new File(folder, f.getFileName()).toPath());
                }
                catch(Exception e) {}
            }
        }
        message = images.size()+" files are added to the deal.";
        messageType = "success";
        businessClient.setDealPhotos(images, String.valueOf(SelectedDealId));
        return "/business/dealphotos.jsf?faces-redirect=true";
    }
    public String removePhoto(int photoid) {
        businessClient.removeDealPhoto(String.valueOf(photoid));
        //this.photoDealChanged();
        return "/business/dealphotos.jsf?faces-redirect=true";
    }
    // End of Deal Photos
    
    // Deals
    public void dealShowChanged() {
        if(DealsShowType == 1) this.showSubmitedDeals();
        else if(DealsShowType == 3) this.showExpiredDeals();
        else if(DealsShowType == 0) this.showAllDeals();
    }
    public String removeDeal(int DealId) {
        businessClient.removeDeal(String.valueOf(DealId));
        return "/business/deals.jsf?faces-redirect=true";
    }
    public String editDeal(int DealId) {
        res = commonClient.getSingleDeal(Response.class, String.valueOf(DealId));
        Dealstb deal = res.readEntity(gdeal);
        DealName = deal.getName();
        IssueDate = deal.getIssueDate();
        DueDate = deal.getDueDate();
        AverageCost = deal.getAverageCost();
        DealsCategory = deal.getDealsCategoryID().getCategoryID();
        image = deal.getBannerImage();
        DealFormId = deal.getDealID();
        return "/business/adddeal.jsf?faces-redirect=true";
    }
    public String submitDeal(int DealId) {
        businessClient.submitDeal(String.valueOf(DealId));
        return "/business/deal.jsf?faces-redirect=true";
    }    
    public void showSubmitedDeals() {
        ShowDealsList = new ArrayList<>();
        for (Dealstb deal : deals) {
            if(deal.getIsVerified() == 1) {
                ShowDealsList.add(deal);
            }
        }        
    }
    public void showAllDeals() {
        ShowDealsList = deals;
    }
    public void showExpiredDeals() {
        ShowDealsList = new ArrayList<>();
        Date todayDate = new Date();
        for (Dealstb deal : deals) {
            if(deal.getDueDate().before(todayDate) ) {
                ShowDealsList.add(deal);
            }
        }
    }
    public void showActiveDeals() {
        ShowDealsList = new ArrayList<>();
        Date todayDate = new Date();
        for (Dealstb deal : deals) {
            if((!deal.getDueDate().before(todayDate)) || deal.getIsVerified() == 2) {
                ShowDealsList.add(deal);
            }
        }        
    }
    // End of Deals
    
    public void resetAlert() {
        message = "";
        messageType = "";
    }
    
    public BDealsBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        message = "";
        messageType = "";
        BusinessId = request.getSession().getAttribute("businessid").toString();
        businessClient = new BusinessClient(token);
        commonClient = new CommonClient();
        gdcategories = new GenericType<Collection<Dealscategorytb>>(){};
        gdeals = new GenericType<Collection<Dealstb>>(){};
        gdealdetail = new GenericType<Dealsdetailstb>(){};
        gdealphotos = new GenericType<Collection<Dealphotostb>>(){};
        gdeal = new GenericType<Dealstb>(){};
    }
    
}
