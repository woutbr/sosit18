package controller;

//import be.hbo5.java.menu.HeaderNavBarLinks;
import be.hbo5.java.menu.MenuItem;
import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import be.hbo5.java.xml.LinksXmlReader;
import com.sun.el.ValueExpressionLiteral;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.bootsfaces.component.dropMenu.DropMenu;
import net.bootsfaces.component.navBarLinks.NavBarLinks;
import net.bootsfaces.component.navLink.NavLink;

/**
 * Deze bean laad de links voor de navbar uit een xml bestand en rendered er een
 * NavBarLinks van. Als een user is ingelogd of uitgelogd moet navBarMenu
 * opnieuw gerendered worden.
 *
 * @author woutbr@student.hik.be
 */
@Named(value = "navBarBean")
@SessionScoped
public class NavBarBean implements Serializable {
    
    @Inject
    private AuthBean authBean;

    private final MenuList links;
//    private HeaderNavBarLinks navBarMenu;

    public NavBarBean() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String linksXmlPath = ext.getRealPath("/templates/navbarlinks.xml");
        this.links = LinksXmlReader.readLinksFromXmlFile(linksXmlPath);
//        this.renderNavBarMenu();
    }

    public MenuList getLinks() {
        return links;
    }
    
    public boolean canDisplayMenuItem(MenuItem menuitem){
        if(authBean.isDebugMode() || menuitem.getRoles().isEmpty()){
            //Als we in debugMode zijn, toon de menuitem altijd.
            //Als de menuitem geen roles heeft, toon het altijd.
            return true;
        }
        return authBean.hasAtLeastOneRole(menuitem.getRoles());
    }

//    public NavBarLinks getNavBarMenu() {
//        return navBarMenu;
//    }

    /**
     * Create a BootsFaces NavBarLinks from the links in the MenuList variable.
     */
//    public final void renderNavBarMenu() {
//        this.navBarMenu = new HeaderNavBarLinks(this.links);
//        List<UIComponent> children = this.navBarMenu.getChildren();
//        for (MenuItem mi : this.links) {
//            if (MenuLink.class.isInstance(mi)) {
//                children.add(createNavLinkFromMenuLink((MenuLink) mi));
//            } else if (MenuList.class.isInstance(mi)) {
//                children.add(createDropMenuFromMenuList((MenuList) mi));
//            }
//        }
//    }

//    private NavLink createNavLinkFromMenuLink(MenuLink menulink) {
//        NavLink navLink = new NavLink();
//        navLink.setValue(menulink.getName());
//        navLink.setOutcome(menulink.getHref());
//        System.out.println(navLink);
//        return navLink;
//    }
//
//    private DropMenu createDropMenuFromMenuList(MenuList menulist) {
//        DropMenu dropmenu = new DropMenu();
//        dropmenu.setValueExpression("value", new ValueExpressionLiteral(menulist.getName(), String.class));
//        List<UIComponent> children = dropmenu.getChildren();
//        for (MenuItem mi : menulist) {
//            if (MenuLink.class.isInstance(mi)) {
//                children.add(createNavLinkFromMenuLink((MenuLink) mi));
//            } else if (MenuList.class.isInstance(mi)) {
//                children.add(createDropMenuFromMenuList((MenuList) mi));
//            }
//        }
//        System.out.println(dropmenu);
//        return dropmenu;
//    }

}
