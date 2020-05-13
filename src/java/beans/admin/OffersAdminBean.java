/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Offertb;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import static jwtrest.Constants.FILE_UPLOAD;

/**
 *
 * @author bhavik
 */
@Named(value = "offersAdminBean")
@SessionScoped
public class OffersAdminBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Offertb>> gOffers;
    Collection<Offertb> offers;
    
    private Part uploadedBannerFile;
    private Part uploadedOfferFile;
    private String folder = FILE_UPLOAD+"offers";
    
    private int id;
    private String name;
    private String code;
    private String terms_conditions;
    private Date issueDate;
    private Date DueDate;
    private String bannerImage;
    private String offerImage;
    private int percentOff;
    private int dollarsOff;
    private String formMessage;

    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
    }

    public Collection<Offertb> getOffers() {
        res = commonClient.getAllOffers(Response.class);
        offers = res.readEntity(gOffers);
        return offers;
    }

    public void setOffers(Collection<Offertb> offers) {
        this.offers = offers;
    }

    public Part getUploadedBannerFile() {
        return uploadedBannerFile;
    }

    public void setUploadedBannerFile(Part uploadedBannerFile) {
        this.uploadedBannerFile = uploadedBannerFile;
    }

    public Part getUploadedOfferFile() {
        return uploadedOfferFile;
    }

    public void setUploadedOfferFile(Part uploadedOfferFile) {
        this.uploadedOfferFile = uploadedOfferFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTerms_conditions() {
        return terms_conditions;
    }

    public void setTerms_conditions(String terms_conditions) {
        this.terms_conditions = terms_conditions;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public int getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(int percentOff) {
        this.percentOff = percentOff;
    }

    public int getDollarsOff() {
        return dollarsOff;
    }

    public void setDollarsOff(int dollarsOff) {
        this.dollarsOff = dollarsOff;
    }
    
    public String showOffers() {
        return "/admin/OffersList.jsf";
    }
    public String addOffer() {
        this.setId(0);
        this.setFormMessage("Add Offer");
        this.setBannerImage("");
        this.setOfferImage("");
        return "/admin/OffersForm.jsf";
    }
    public String editOffer(int id) {
        this.setId(id);
        this.setFormMessage("Edit Offer");
        for (Offertb offer : offers) {
            if(offer.getOfferID() == id) {
                this.setTerms_conditions(offer.getTermsConditions());
                this.setName(offer.getName());
                this.setIssueDate(offer.getIssueDate());
                this.setDueDate(offer.getDueDate());
                this.setPercentOff(offer.getPercentOff());
                this.setDollarsOff(offer.getDollarsOff());
                this.setCode(offer.getCode());
                this.setBannerImage(offer.getBannerImage());
                this.setOfferImage(offer.getOfferImage());
            }
        }
        return "/admin/OffersForm.jsf";
    }
    public String removeOffer(int id) {
        adminClient.removeOffer(String.valueOf(id));
        return "/admin/OffersList.jsf";
    }
    public String submit() {
        if(uploadedBannerFile != null) {
            this.uploadImage(uploadedBannerFile);
            this.setBannerImage(uploadedBannerFile.getSubmittedFileName());
        }
        if(uploadedOfferFile != null) {
            this.uploadImage(uploadedOfferFile);
            this.setOfferImage(uploadedOfferFile.getSubmittedFileName());
        }
        
        Offertb offer = new Offertb();
        offer.setOfferID(id);
        offer.setName(name);
        offer.setCode(code);
        offer.setDollarsOff(dollarsOff);
        offer.setPercentOff(percentOff);
        offer.setIssueDate(issueDate);
        offer.setDueDate(DueDate);
        offer.setTermsConditions(terms_conditions);
        offer.setBannerImage(bannerImage);
        offer.setOfferImage(offerImage);
        if(id == 0)
            adminClient.addOffer(offer);
        else
            adminClient.editOffer(offer, String.valueOf(id));
        return "/admin/OffersList.jsf";
    }
    public void uploadImage(Part uploadedFile) {
        try (InputStream input = uploadedFile.getInputStream()) {
            String image = uploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, image).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public OffersAdminBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gOffers = new GenericType<Collection<Offertb>>(){};
    }
    
}
