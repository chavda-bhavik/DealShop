/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Dealscategorytb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
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
@Named(value = "dealsCategoryAdminBean")
@SessionScoped
public class DealsCategoryBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    GenericType<Collection<Dealscategorytb>> gDealCategories;
    Collection<Dealscategorytb> dealsCategories;
    
    private Part uploadedFile;
    private String folder = FILE_UPLOAD+"dealscategory";
    
    private int id;
    private String name;
    private String image;
    private String formMessage;

    public Collection<Dealscategorytb> getDealsCategories() {
        res = commonClient.getDealsCategory(Response.class);
        dealsCategories = res.readEntity(gDealCategories);
        return dealsCategories;
    }

    public void setDealsCategories(Collection<Dealscategorytb> dealsCategories) {
        this.dealsCategories = dealsCategories;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
    }
    public String addCategory() {
        this.setId(0);
        this.setFormMessage("Add Deals Category");
        return "/admin/DealsCategoryForm.jsf";
    }
    public String editCategory(int id) {
        this.setId(id);
        this.setFormMessage("Edit Deals Category");
        for (Dealscategorytb dealsCategory : dealsCategories) {
            if(dealsCategory.getCategoryID() == id) {
                this.setImage(dealsCategory.getImage());
                this.setName(dealsCategory.getName());
            }
        }
        return "/admin/DealsCategoryForm.jsf";
    }
    public String removeCategory(int id) {
        adminClient.removeDealsCategory(String.valueOf(id));
        return "/admin/DealsCategoryList.jsf";
    }
    public String submit() {
        if(uploadedFile != null) {
            this.saveFile();
        }
        Dealscategorytb dcategory = new Dealscategorytb();
        dcategory.setCategoryID(id);
        dcategory.setImage(image);
        dcategory.setName(name);
        if(id==0) {
            adminClient.addDealsCategory(dcategory);
        } else {
            adminClient.editDealsCategory(dcategory, String.valueOf(id));
        }
        return "/admin/DealsCategoryList.jsf";
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
    public String showCategories() {
        return "/admin/DealsCategoryList.jsf";
    }
    public DealsCategoryBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gDealCategories = new GenericType<Collection<Dealscategorytb>>(){};
    }
    
}
