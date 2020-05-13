/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import client.UserClient;
import entity.Usertb;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author bhavik
 */
@Named(value = "userCDIBean")
@RequestScoped
public class UserCDIBean {
    
    @Inject javax.security.enterprise.SecurityContext securityContext;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    ExternalContext externalContext = facesContext.getExternalContext();
    
    UserClient userClient;
    CommonClient commonClient;
    Response res;
    Pbkdf2PasswordHashImpl pbkd;
    
    Boolean isUserAuthenticated=false;
    String UserActionMessage="";
    
    GenericType<Usertb> gUser;
    Usertb loginUser;
    
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public Boolean getIsUserAuthenticated() {
        return isUserAuthenticated;
    }

    public String getUserActionMessage() {
        return UserActionMessage;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void getAndSetLoginCredentials(String email) {
        System.out.println("Setting credentials");
        HttpSession session = request.getSession(true);
        res = commonClient.getLoginUser(Response.class, email);
        loginUser = res.readEntity(gUser);

        session.setAttribute("useremail", loginUser.getEmail());
        session.setAttribute("role", loginUser.getUserCategoryID().getName());
        session.setAttribute("userid", loginUser.getUserID());
        session.setAttribute("username", loginUser.getName());
    }
    
    public String login() {
        String returnPage = "";
        try{
            Credential credential = new UsernamePasswordCredential(email, new Password(password));

            AuthenticationStatus status= securityContext.authenticate(request, response, withParams().credential(credential));       
            if(status.equals(SUCCESS))
            {
                if(securityContext.isCallerInRole("Admin"))
                {
                    System.out.println("Admin Login Success!");
                    returnPage = "/admin/Home.jsf?faces-redirect=true";
                }
                else if(securityContext.isCallerInRole("Business"))
                {
                    System.out.println("Business Login Success!");
                    returnPage = "/business/Home.jsf?faces-redirect=true";
                } 
                else if(securityContext.isCallerInRole("User"))
                {
                   System.out.println("user Login Success!");
                   returnPage = "/user/Home.jsf";
                } else {
                    System.out.println("Role Not Found!");
                    UserActionMessage = "User Role Not Founed !!!";
                    returnPage = "/user/Login.jsf";
                }
            }
            else if(status.equals(SEND_FAILURE))
            {
                UserActionMessage = "Email/Password is wrong !!!";
                returnPage = "/user/Login.jsf";
            }    
        }
        catch (Exception e)
        {
            UserActionMessage = "Oops! Something went wrong!!!";
            e.printStackTrace();
            returnPage = "/user/Login.jsf";
        }
        return returnPage;
    }
    
    public String register() {
        Usertb ur = new Usertb();
        ur.setEmail(email);
        ur.setName(username);
        ur.setPassword(pbkd.generate(password.toCharArray()));
        userClient.addUser(ur);
        return "/user/Login.jsf";
    }
    
    public String goToLogin() {
        String page = "/user/Login.jsf";
        if(this.isUserAuthenticated) {
            page = this.goToHomePage();
        }
        return page;
    }
    
    public String goToRegister() {
        return "/user/Register.jsf";
    }
    
    public void logout() throws ServletException {
        request.logout();
        session.removeAttribute("useremail");
        session.removeAttribute("role");
        session.removeAttribute("userid");
        session.removeAttribute("username");
    }
    
    public Boolean isUserLoggedIn() {
        return session.getAttribute("role") == null ? false : true;
    }
    
    public String getLoginUserName() {
        return session.getAttribute("username").toString();
    }
    
    public String getLoginUserEmail() {
        return session.getAttribute("useremail").toString();
    }
    
    public int getLoginUserId() {
        return Integer.valueOf(session.getAttribute("userid").toString());
    }
    
    public String goToHomePage() {
        String homepage = "/user/Home.jsf";
        if(session.getAttribute("role") == "Business") {
            homepage = "/business/Home.jsf";
        } else if(session.getAttribute("role") == "Admin") {
            homepage = "/admin/Home.jsf";
        } else {
            homepage = "/user/Home.jsf";
        }
        return homepage;
    }
    
    public Usertb getLoginUser() {
        return loginUser;
    }
    
    public void checkLoginAndRedirect() throws IOException {
        Object h = session.getAttribute("userid");
        if(h==null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/user/Home.jsf");
        }
    }
    
    public UserCDIBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";
        token = request.getSession().getAttribute("token").toString();
        
        userClient = new UserClient(token);
        commonClient = new CommonClient();
        pbkd = new Pbkdf2PasswordHashImpl();
        gUser = new GenericType<Usertb>(){};
        UserActionMessage = "";
    }
    
}
