/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Businesscategorytb;
import entity.Businesstypetb;
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
@Named(value = "bTypeBean")
@SessionScoped
public class BTypeBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    GenericType<Collection<Businesstypetb>> gBtypes;
    Collection<Businesstypetb> bTypes;
    private Part uploadedFile;
    private String folder = FILE_UPLOAD+"businesstype";
    
    private int id;
    private String Name;
    private String image;
    private int categoryId;
    private String formMessage = "Add Business Type";

    public Collection<Businesstypetb> getbTypes() {
        res = commonClient.getAllBusinessTypes(Response.class);
        bTypes = res.readEntity(gBtypes);
        return bTypes;
    }

    public void setbTypes(Collection<Businesstypetb> bTypes) {
        this.bTypes = bTypes;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String Image) {
        this.image = Image;
    }
    
    public String addType() {
        this.setId(0);
        this.setImage("");
        this.setFormMessage("Add Business Type");
        return "/admin/BTypeForm.jsf";
    }
    public String removeType(int id) {
        return "/admin/BTypeList.jsf";
    }
    public String editType(int id) {
        this.setId(id);
        this.setFormMessage("Edit Business Type");
        for (Businesstypetb bType : bTypes) {
            if(bType.getBusinessTypeID() == id) {
                this.setName(bType.getName());
                this.setImage(bType.getImage());
            }
        }
        return "/admin/BTypeForm.jsf";
    }
    public String showType() {
        return "/admin/BTypeList.jsf";
    }
    
    public String submit() {
        if(uploadedFile != null) {
            this.saveFile();
            image = uploadedFile.getSubmittedFileName();
        }
        Businesscategorytb category = new Businesscategorytb();
        category.setCategoryID(categoryId);
        Businesstypetb btype = new Businesstypetb();
        btype.setName(Name);
        btype.setImage(image);
        btype.setBusinessTypeID(id);
        btype.setBusinessCategoryID(category);
        if(id == 0) {
            System.out.println("add");
            adminClient.addBusinessType(btype);
        } else {
            System.out.println("edit");
            adminClient.editBusinessType(btype, String.valueOf(id));
        }
        return "/admin/BTypeList.jsf";
    }
    
    public void saveFile() {
        try (InputStream input = uploadedFile.getInputStream()) {
            Files.copy(input, new File(folder, image).toPath());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    
    public BTypeBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gBtypes = new GenericType<Collection<Businesstypetb>>(){};
    }
    
}
