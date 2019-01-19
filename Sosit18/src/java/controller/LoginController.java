package controller;

import dao.UseraccountFacade;
import entity.Useraccount;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;

/**
 * Source: How to handle authentication/authorization with users in a database?
 * https://stackoverflow.com/a/9969415 Performing user authentication in Java EE
 * / JSF using j_security_check : answer by BalusC
 * https://stackoverflow.com/a/2207147
 *
 * @author woutbr@student.hik.be
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private UseraccountFacade useraccountFacade;
    @Inject
    private AuthBean authBean;

    private String username;
    private String password;

    public LoginController() {
        this.clearUsernamePassword();
    }

    public void onload() {
        this.prepareMessages();
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void UserBean(AuthBean useraccount) {
        this.authBean = useraccount;
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
     * If no user is logged in, validate the username and password. On succes,
     * redirect. Else add an error message.
     *
     * @throws IOException If the redirect can't be executed.
     */
    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (this.authBean.isLoggedIn()) {
            FacesMessage loginErrorMessage = new FacesMessage("A user is still logged in.");
            context.addMessage(null, loginErrorMessage);

        } else {
            ExternalContext externalContext = context.getExternalContext();
            Useraccount foundUser = this.useraccountFacade.findByUsernamePassword(this.username, this.password);

            if (foundUser != null) {
                // User met zelfde username en password is gevonden
                this.authBean.setUser((foundUser));
                // Als de user is geredirect naar de login pagina, redirect dan terug.
                String redirectUrl = LoginController.originalURL(externalContext);
                externalContext.redirect(redirectUrl);

            } else {
                FacesMessage loginErrorMessage = new FacesMessage("Username or password are incorrect.");
                context.addMessage(null, loginErrorMessage);
            }
        }
    }

    /**
     * Get the original request URI and any possible query string.
     * https://stackoverflow.com/a/2207147
     *
     * @param externalContext Can be found by
     * FacesContext.getCurrentInstance().getExternalContext();
     * @return An URL as a String
     */
    static public String originalURL(ExternalContext externalContext) {
        String originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
        return originalURL;
    }

    /**
     * Momenteel wordt LogoutServlet gebruikt.
     *
     * @throws IOException
     */
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        this.authBean.clearUser();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    
    private void prepareMessages(){
        final Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap();
        List<String> msgs = (List<String>)sessionMap.get("loginMessages");
        this.createFacesMessages(msgs);
        sessionMap.remove("loginMessages");
    }
    
    /**
     * Create FacesMessages from a list of strings.
     * https://stackoverflow.com/a/48228893 | answer Oscar Pérez
     */
    private void createFacesMessages(List<String> msgs) {
        if (msgs != null) {
            msgs.forEach((msg) -> {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(msg));
            });
        }
    }
}
