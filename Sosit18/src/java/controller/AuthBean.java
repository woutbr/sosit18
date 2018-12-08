package controller;

import entity.Useraccount;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "auth")
@SessionScoped
public class AuthBean implements Serializable {

    private Useraccount user;
    private boolean debugMode;

    public AuthBean() {
        this.debugMode = true;//For testing. Ignores the logincheck in LoginFilter
    }

    public Useraccount getUser() {
        return user;
    }

    public void setUser(Useraccount user) {
        this.user = user;
    }
    
    public void clearUser() {
        this.user = null;
    }
    
    public boolean isLoggedIn(){
        return this.user != null;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

}
