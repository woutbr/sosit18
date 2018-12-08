package controller;

import dao.UseraccountFacade;
import entity.Useraccount;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 * Source: How to handle authentication/authorization with users in a database?
 * https://stackoverflow.com/a/9969415
 *
 * @author woutbr@student.hik.be
 */
@Named(value = "auth")
@SessionScoped
public class AuthBean implements Serializable {

    @EJB
    private UseraccountFacade useraccountFacade;
    private Useraccount useraccount;
    private String username;
    private String password;

    public AuthBean() {
        this.clearUsernamePassword();
        this.useraccount = null;
    }

    public Useraccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
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

    private void clearUsernamePassword() {
        this.username = this.password = "";
    }

    /**
     * TODO hash password. Moet dezelfde functie zijn als bij registratie
     *
     * @param password
     * @return A string of the hashed password.
     */
    private String hashPassword(String password) {
        return password;
    }

    /**
     * Validates the properties username and password by getting a Useraccount
     * from the db with the equal username. It then compares the password with
     * the password of the found Useraccount. If the passwords are a match the
     * property useraccount is set with the found useraccount.
     *
     * @return True if a Useraccount with an equal password has been found.
     */
    public boolean validateUsernamePassword() {
        if (this.username != null && this.password != null) {
            Useraccount foundUseraccount = null;
            foundUseraccount = this.useraccountFacade.findByUsername(this.username);
            if (foundUseraccount != null) {
                String hashedPassword = this.hashPassword(this.password);
                if (foundUseraccount.getPassword().equals(hashedPassword)) {
                    this.useraccount = foundUseraccount;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * If no user is logged in, validate the username and password.
     * On succes, redirect to index. Else add an error message.
     * @return A String representing a page.
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (useraccount != null) {
            FacesMessage loginErrorMessage = new FacesMessage("A user is still logged in.");
            context.addMessage(null, loginErrorMessage);
        } else {
            if (validateUsernamePassword()) {
                this.clearUsernamePassword();
                //https://stackoverflow.com/a/8024427
                //When user is redirected to the loginpage, 
                //the following code can send the user on its original way;
//                String redirect = context.getExternalContext().getRequestParameterMap().get("redirect");
//                if(redirect != null && redirect != "null"){
//                    System.out.println("redirect from login:"+redirect);
//                    return redirect + "?faces-redirect=true" ;
//                }else{
                return "index?faces-redirect=true";
//                }
            } else {
                FacesMessage loginErrorMessage = new FacesMessage("Username or password are incorrect.");
                context.addMessage(null, loginErrorMessage);
            }
        }
        return "";
    }
}
