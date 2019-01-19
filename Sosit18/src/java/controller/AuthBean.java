package controller;

import entity.Role;
import entity.Useraccount;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This managed bean holds the currently logged in Useraccount. It can also be
 * asked if a user has a certain Role or permission.
 *
 * @author woutbr@student.hik.be
 */
@Named(value = "auth")
@SessionScoped
public class AuthBean implements Serializable {

    private Useraccount user;
    private boolean debugMode;
    private String currentTheme = "default";
    private static final List<String> bootswatchThemes = Arrays.asList( 
            "cerulean", "cosmo", "cyborg", "darkly", "flatly", "journal", "lumen", 
            "sandstone", "simplex", "slate", "spacelab", "superhero", "united", "yeti");

    public AuthBean() {
        this.debugMode = false;//For testing. Ignores the logincheck in LoginFilter when true
    }

    public boolean isDebugMode() {
        int a=1;
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    public List<String> getBootswatchThemes() {
        return bootswatchThemes;
    }

    public Useraccount getUser() {
        return user;
    }

    public void setUser(Useraccount user) {
        this.user = user;
    }

    /**
     * Zet het user object op null. Zo geeft isLoggedIn false terug.
     */
    public void clearUser() {
        this.user = null;
    }

    /**
     * Geeft true wanneer auth.user niet null is.
     */
    public boolean isLoggedIn() {
        return this.user != null;
    }

    /**
     * Is een UserAccount ingelogd en heeft het een Permission met canedit.
     */
    public boolean canEdit() {
        return this.isLoggedIn() 
            && this.user.getCanedit();
    }

    /**
     * Is een UserAccount ingelogd en heeft het een Permission met canread.
     */
    public boolean canRead() {
        return this.isLoggedIn() 
            && this.user.getCanread();
    }

    /**
     * Is een UserAccount ingelogd en heeft het een Permission met caninsert.
     */
    public boolean canInsert() {
        return this.isLoggedIn() 
            && this.user.getCaninsert();
    }

    /**
     * Is een UserAccount ingelogd en heeft het een Permission met candelete.
     */
    public boolean canDelete() {
        return this.isLoggedIn() 
            && this.user.getCandelete();
    }

    /**
     * Is een UserAccount ingelogd en heeft een Admin rol.
     *
     * @return true als de user een Admin rol heeft.
     */
    public boolean isAdmin() {
        return this.isLoggedIn() 
            && this.user.hasRole(new Role("Admin"));
    }

    /**
     * Is een UserAccount ingelogd en heeft een Supporter rol.
     *
     * @return true als de user een Supporter rol heeft.
     */
    public boolean isSupporter() {
        return this.isLoggedIn() 
            && this.user.hasRole(new Role("Supporter"));
    }

    /**
     * Is een UserAccount ingelogd en heeft een User rol.
     *
     * @return true als de user een User rol heeft.
     */
    public boolean isGewoneUser() {
        return this.isLoggedIn() 
            && this.user.hasRole(new Role("User"));
    }
    
    /**
     * Is een UserAccount ingelogd en heeft het minstens één van de gegeven rollen.
     * 
     * @param roles Rollen welke vergeleken worden
     * @return true als de user één van de rollen heeft.
     */
    public boolean hasAtLeastOneRole(List<String> roles) {
        return this.isLoggedIn() 
            && roles.stream().anyMatch((role) -> 
                    this.user.hasRole(new Role(role)));
    }
    
    /**
     * Methode die een boolean terug geeft afhankelijk van z'n userrole.
     * @param user Returned als de User een user is
     * @param supporter Returned als de User een supporter is
     * @param admin Returned als de User een admin is
     * @return Eén van de gegeven booleans afhankelijk van de role 
     * van de ingelogde user
     */
    public boolean rights(boolean user, boolean supporter, boolean admin){
        if (isGewoneUser()) {
            return user;
        }else if (isSupporter()) {
            return supporter;
        }else{
            return admin;
        }
    }
}
