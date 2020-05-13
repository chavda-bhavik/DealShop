/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
import entity.Citytb;
import entity.Statetb;
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
@Named(value = "cityBean")
@SessionScoped
public class CityBean implements Serializable {
    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    GenericType<Collection<Citytb>> gCities;
    Collection<Citytb> cities;
    
    private String formMessage = "Add City";
    private String Name;
    private int id;
    private int selectedStateId;

    public int getSelectedStateId() {
        return selectedStateId;
    }

    public void setSelectedStateId(int selectedStateId) {
        this.selectedStateId = selectedStateId;
    }

    public Collection<Citytb> getCities() {
        res = commonClient.getAllCities(Response.class);
        cities = res.readEntity(gCities);
        return cities;
    }

    public void setCities(Collection<Citytb> cities) {
        this.cities = cities;
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
    public String editCity(int id) {
        this.setFormMessage("Edit City");
        this.setId(id);
        Citytb city = new Citytb();
        for (Citytb ct : cities) {
            if(ct.getCityID() == id) {
                city = ct;
            }
        }
        this.setName(city.getName());
        this.setSelectedStateId(city.getStateID().getStateID());
        return "/admin/CityForm.jsf";
    }
    public String removeCity(int id) {
        adminClient.removeCity(String.valueOf(id));
        return "/admin/CityList.jsf";
    }
    public String addCity() {
        this.setId(0);
        this.setFormMessage("Add City");
        return "/admin/CityForm.jsf";
    }
    public String showCities() {
        return "/admin/CityList.jsf";
    }
    public String submit() {
        Citytb city = new Citytb();
        city.setName(Name);
        city.setCityID(id);
        Statetb state = new Statetb();
        state.setStateID(selectedStateId);
        city.setStateID(state);
        if(this.getId() == 0) {
            System.out.println("Adding City"+id);
            adminClient.addCity(city);
        } else {
            System.out.println(selectedStateId+" Editing City"+id);
            adminClient.editCity(city, String.valueOf(id));
        }
        return "/admin/CityList.jsf";
    }
    public void refreshCityList() {
        res = commonClient.getAllCities(Response.class);
        cities = res.readEntity(gCities);
    }
    public CityBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gCities = new GenericType<Collection<Citytb>>(){};
    }
    
}
