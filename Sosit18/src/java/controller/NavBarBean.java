package controller;

//import be.hbo5.java.menu.HeaderNavBarLinks;
import be.hbo5.java.menu.MenuItem;
import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import be.hbo5.java.xml.LinksXmlReader;
import com.sun.el.ValueExpressionLiteral;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import net.bootsfaces.component.dropMenu.DropMenu;
import net.bootsfaces.component.navBarLinks.NavBarLinks;
import net.bootsfaces.component.navLink.NavLink;
import org.xml.sax.SAXException;

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

    public NavBarBean() throws SAXException, IOException {
        //DEBUG Print the files on the classpath
//        System.out.println("getResourceFiles: "+getResourceFiles("/"));

        try {
//Get the xml file from the jar.
            String pathNavbarlinks = "/navbarlinks";
            InputStream navbarlinksXmlStream = getResourceAsStream(pathNavbarlinks + ".xml");
            InputStream navbarlinksDtdStream = getResourceAsStream(pathNavbarlinks + ".dtd");

//ServletContext Stream
//        ExternalContext ext2 = FacesContext.getCurrentInstance().getExternalContext();
//        ServletContext servletContext = (ServletContext) ext2.getContext();
//        realPath = servletContext.getRealPath(path);
//        System.out.println("ServletContext realPath: " + realPath);
//        navbarlinksXmlStream = servletContext.getResourceAsStream(path);
//        System.out.println("ServletContext NavBarBean: " + navbarlinksXmlStream);

//FacesContext string path
//        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
//        realPath = ext.getRealPath(path);
//        System.out.println("FacesContext realPath: " + realPath);
            this.links = LinksXmlReader.readLinksFromXmlFile(navbarlinksXmlStream, navbarlinksDtdStream);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(NavBarBean.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public MenuList getLinks() {
        return links;
    }

    /**
     * Can the given MenuItem be displayed for the current user.
     *
     * @param menuitem MenuItem for which to check
     * @return true if in debugMode, the given menuitem has no roles or it has a
     * role equal to the roles of the current user
     */
    public boolean canDisplayMenuItem(MenuItem menuitem) {
        if (authBean.isDebugMode() || menuitem.getRoles().isEmpty()) {
            //Als we in debugMode zijn, toon de menuitem altijd.
            //Als de menuitem geen roles heeft, toon het altijd.
            return true;
        }
        return authBean.hasAtLeastOneRole(menuitem.getRoles());
    }

    /**
     * Returns an input stream for reading the specified resource. First tries
     * the find on ClassLoader. If not found try from this class.
     *
     * @param resource The resource name
     * @return An InputStream object or null if no resource with this name is
     * found
     */
    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Get a list of resources from classpath directory
     * https://stackoverflow.com/a/3923685 : answer iirekm
     *
     * @param path A path to an directory under the classpath found with
     * getContextClassLoader or getClass.
     * @return ArrayList of filenames found on path
     * @throws IOException if an I/O error occurs
     */
    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
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
