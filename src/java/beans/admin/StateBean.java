/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import client.AdminClient;
import client.CommonClient;
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
@Named(value = "stateBean")
@SessionScoped
public class StateBean implements Serializable {

    AdminClient adminClient;
    CommonClient commonClient;
    Response res;
    
    GenericType<Collection<Statetb>> gStates;
    Collection<Statetb> states;
    private String name;
    private String formMessage;
    private int id;

    public String getFormMessage() {
        return formMessage;
    }

    public void setFormMessage(String formMessage) {
        this.formMessage = formMessage;
    }

    public Collection<Statetb> getStates() {
        res = commonClient.getAllStates(Response.class);
        states = res.readEntity(gStates);
        return states;
    }

    public void setStates(Collection<Statetb> states) {
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String editState(int id) {
        this.setFormMessage("Edit State");
        this.setId(id);
        Statetb state = new Statetb();
        for (Statetb st : states) {
            if(st.getStateID() == id) {
                state = st;
            }
        }
        this.setName(state.getName());
        return "/admin/StateForm.jsf";
    }
    public String removeState(int id) {
        adminClient.removeState(String.valueOf(id));
        return "/admin/statelist.jsf";
    }
    public String addState() {
        this.setId(0);
        this.setFormMessage("Add State");
        return "/admin/StateForm.jsf";
    }
    public String showStates() {
        return "/admin/statelist.jsf";
    }
    public String submit() {
        Statetb state = new Statetb();
        state.setName(name);
        System.out.println("Submitted");
        if(this.getId() == 0) {
            System.out.println("Adding State");
            adminClient.addState(state);
        } else {
            System.out.println("Editing State");
            adminClient.editState(state, String.valueOf(id));
        }
        return "/admin/statelist.jsf";
    }
    public StateBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        adminClient = new AdminClient(token);
        commonClient = new CommonClient();
        gStates = new GenericType<Collection<Statetb>>(){};
    }
    
}
