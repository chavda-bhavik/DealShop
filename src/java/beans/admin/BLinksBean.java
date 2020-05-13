/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Linkstb;
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
@Named(value = "bLinksBean")
@SessionScoped
public class BLinksBean implements Serializable {
    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Linkstb>> gLinks;
    Collection<Linkstb> links;
    
    private Part uploadedFile;
    private String folder = FILE_UPLOAD+"links";
    
    private int id;
    private String name;
    private String logo;
    private String formMessage;

    public Collection<Linkstb> getLinks() {
        res = commonClient.getLinks(Response.class);
        links = res.readEntity(gLinks);
        return links;
    }

    public void setLinks(Collection<Linkstb> links) {
        this.links = links;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
    }
    public String showLinks() {
        return "/admin/BLinksList.jsf";
    }
    public String addLink() {
        this.setId(0);
        this.setFormMessage("Add Link");
        this.setLogo("");
        return "/admin/BLinksForm.jsf";        
    }
    public String editLink(int id) {
        this.setId(id);
        this.setFormMessage("Edit Link");
        for (Linkstb link : links) {
            if(link.getLinkID() == id) {
                this.setName(link.getName());
                this.setLogo(link.getLogo());
            }
        }
        return "/admin/BLinksForm.jsf";
    }
    public String removeLink(int id) {
        adminClient.removeLink(String.valueOf(id));
        return "/admin/BLinksList.jsf";
    }
    public String submit() {
        if(uploadedFile != null) {
            this.saveFile();
        }
        Linkstb link = new Linkstb();
        link.setLinkID(id);
        link.setLogo(logo);
        link.setName(name);
        if(id == 0) {
            adminClient.addLink(link);
        } else {
            adminClient.editLink(link, String.valueOf(id));
        }
        return "/admin/BLinksList.jsf";
    }
    public void saveFile() {
        try (InputStream input = uploadedFile.getInputStream()) {
            logo = uploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, logo).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
            //return null;
        }
    }
    public BLinksBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gLinks = new GenericType<Collection<Linkstb>>(){};
    }
}
