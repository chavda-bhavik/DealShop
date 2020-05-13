/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Informationtb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhavik
 */
@Named(value = "bInfoBean")
@SessionScoped
public class BInfoBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Informationtb>> gInfos;
    Collection<Informationtb> infos;
    
    private int id;
    private String name;
    private String formMessage;

    public Collection<Informationtb> getInfos() {
        res = commonClient.getBusinessInfoList(Response.class);
        infos = res.readEntity(gInfos);
        return infos;
    }

    public void setInfos(Collection<Informationtb> infos) {
        this.infos = infos;
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

    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
    }
    public String showInfo() {
        return "/admin/BInfoList.jsf";
    }
    public String addInfo() {
        this.setId(0);
        this.setFormMessage("Add Business Information");
        return "/admin/BInfoForm.jsf";
    }
    public String editInfo(int id) {
        this.setId(id);
        for (Informationtb info : infos) {
            if(info.getInformationID() == id) {
                this.setName(info.getTitle());
            }
        }
        this.setFormMessage("Edit Business Information");
        return "/admin/BInfoForm.jsf";
    }
    public String submit() {
        Informationtb info = new Informationtb();
        info.setTitle(name);
        info.setInformationID(id);
        if(this.getId() == 0) {
            adminClient.addInformation(info);
        } else {
            adminClient.editInformation(info, String.valueOf(id));
        }
        return "/admin/BInfoList.jsf";
    }
    public String removeInfo(int id) {
        adminClient.removeInformation(String.valueOf(id));
        return "/admin/BInfoList.jsf";
    }
    public BInfoBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gInfos = new GenericType<Collection<Informationtb>>(){};
    }
    
}
