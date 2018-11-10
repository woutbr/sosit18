package controller;

import be.hbo5.java.menu.MenuList;
import be.hbo5.java.xml.LinksXmlReader;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "NavBarController")
public class NavBarController {
    //TODO Rename to NavBarBean

    private final MenuList links;

    public NavBarController() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String linksXmlPath = ext.getRealPath("/templates/navbarlinks.xml");
        this.links = LinksXmlReader.readLinksFromXmlFile(linksXmlPath);
    }

    public MenuList getLinks() {
        return links;
    }

}
