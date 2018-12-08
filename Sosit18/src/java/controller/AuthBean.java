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

}
