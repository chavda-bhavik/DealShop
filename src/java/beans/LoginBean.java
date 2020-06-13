/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.CommonClient;
import entity.Usertb;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author bhavik
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @Inject private SecurityContext securityContext;

    //FacesContext facesContext = FacesContext.getCurrentInstance();
    //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    //HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    Pbkdf2PasswordHashImpl pbkd;
    CommonClient commonClient;
    
//    BusinessBean businessBean;
    UserCDIBean userCDIBean;
    private String email;
    private String username;
    private String password;
    private String confirmpassword;
    private String message;
    private AuthenticationStatus status;
    private Set<String> roles;

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public LoginBean() {
        pbkd = new Pbkdf2PasswordHashImpl();
        commonClient = new CommonClient();
//        businessBean = new BusinessBean();
    }
    
    public void checkLoginAndRedirect() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Object h = request.getSession().getAttribute("token");
        if(h==null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/user2/Home.jsf");
        }
    }
    
    public Boolean isUserLoggedIn() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println(request.getSession().getAttribute("token"));
        return request.getSession().getAttribute("token") != null;
    }
    
    public String goToLogin() {
        return "/user2/Login.jsf?faces-redirect=true";
    }
    
    public String goToUserRegister() {
        return "/user/Register.jsf?faces-redirect=true";
    }
    
    public String goToBusinessRegister() {
        return "/business/Register.jsf?faces-redirect=true";
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            request.getSession().setAttribute("logged-group", ""); 
            Credential credential = new UsernamePasswordCredential(email, new Password(password));
            AuthenticationStatus status= securityContext.authenticate(request, response, withParams().credential(credential));                               
     
            if (status.equals(SEND_CONTINUE)) {
                context.responseComplete();
            }

            userCDIBean = new UserCDIBean();
            userCDIBean.getAndSetLoginCredentials(email);
            
            System.out.println("In bean");
            if(roles.contains("Admin"))
            {
                System.out.println("In admin");
                request.getSession().setAttribute("logged-group", "Admin");
                return "/admin/Home.jsf?faces-redirect=true";
            }
            else if(roles.contains("User"))
            {
                System.out.println("In User");
                request.getSession().setAttribute("logged-group", "User");
                return "/user2/Home.jsf";
            }
            else if(roles.contains("Business"))
            {
                System.out.println("In Business");
//                businessBean.getAndSetBusinessDetails(email);
                request.getSession().setAttribute("logged-group", "Business");
                return "/business/Home.jsf?faces-redirect=true";
            }
        }
        catch (Exception e)
        {
            message = "Email/password is wrong !!!";
            e.printStackTrace();
        }
        return "/user2/Login.jsf";
    }
   
    public String registerUser() {
        Usertb ur = new Usertb();
        ur.setEmail(email);
        ur.setName(username);
        ur.setPassword(pbkd.generate(password.toCharArray()));
        commonClient.registerCommonUser(ur);
        return "/user2/Login.jsf";
    }
    
    public String getTimeDate(Date date) throws ParseException {
        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "ag", "sep", "oct", "nov", "dec"};
        String oldDate = date.toString();
        DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        Date testDate = (Date) format.parse(oldDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(testDate);
        String temp = monthNames[cal.get(Calendar.MONTH) + 1]+" "+cal.get(Calendar.DATE)+", "+cal.get(Calendar.YEAR);
        return temp;
    }
    
    private static void addError(FacesContext context, String message) {
        context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(SEVERITY_ERROR, message, null));
    }
    
    public void logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("logged-group");
        request.getSession().removeAttribute("businessid");
        
        request.logout();
        request.getSession().invalidate();  
        //return "/user/Home.jsf";
    }
    public String logoutUser() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("logged-group");
        session.removeAttribute("useremail");
        session.removeAttribute("role");
        session.removeAttribute("userid");
        session.removeAttribute("username");
        
        request.logout();
        request.getSession().invalidate();
        return "/user2/Home.jsf";
    }
}
