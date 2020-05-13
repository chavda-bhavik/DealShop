/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Businesscategorytb;
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
@Named(value = "bCategoryBean")
@SessionScoped
public class BCategoryBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    GenericType<Collection<Businesscategorytb>> gBCategories;
    Collection<Businesscategorytb> bCategories;
    
    private Part uploadedFile;
    private String folder = FILE_UPLOAD+"businesscategory/";
    //"/media/bhavik/DATA1/Sem8/DealShop/DealShop-war/web/assets/images/"
    
    private int id;
    private String name;
    private String image;
    private String formMessage = "Add Business Category";

    public Collection<Businesscategorytb> getbCategories() {
        res = commonClient.getBusinessCategory(Response.class);
        bCategories = res.readEntity(gBCategories);
        return bCategories;
    }

    public void setbCategories(Collection<Businesscategorytb> bCategories) {
        this.bCategories = bCategories;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
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
    public String editCategory(int id) {
        this.setId(id);
        this.setFormMessage("Edit Business Category");
        for (Businesscategorytb bCategory : bCategories) {
            if(bCategory.getCategoryID() == id) {
                this.setName(bCategory.getName());
                this.setImage(bCategory.getImage());
            }
        }
        return "/admin/BCategoryForm.jsf";        
    }
    public String removeCategory(int id) {
        adminClient.removeBusinessCategory(String.valueOf(id));
        return "/admin/BCategoryList.jsf";
    }
    public String showCategory() {
        return "/admin/BCategoryList.jsf";
    }
    public String submit() {
        if(uploadedFile != null) {
            image = this.saveFile();
        }
        Businesscategorytb bcategory = new Businesscategorytb();
        bcategory.setName(name);
        bcategory.setImage(image);
        if(id == 0) {
            adminClient.addBusinessCategory(bcategory);
        } else {
            adminClient.editBusinessCategory(bcategory, String.valueOf(id));
        }
        return "/admin/BCategoryList.jsf";
    }
    public String saveFile() {
        try (InputStream input = uploadedFile.getInputStream()) {
            image = uploadedFile.getSubmittedFileName();
            System.out.println("Uploaded file with: "+image);
            Files.copy(input, new File(folder, image).toPath());
            return image;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String addCategory() {
        this.setId(0);
        this.setFormMessage("Add Business Category");
        return "/admin/BCategoryForm.jsf";
    }
    
    public BCategoryBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gBCategories = new GenericType<Collection<Businesscategorytb>>(){};
    }
    
}
