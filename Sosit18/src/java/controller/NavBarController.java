package controller;

import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author woutbr@student.hik.be
 */
@Named(value = "NavBarController")
public class NavBarController {

    private final MenuList links;

    public NavBarController() {
//        TODO Read from xml
        this.links = new MenuList((""));
        this.links.add(new MenuLink("Tickets", "tickets"));

        MenuList companyLink = new MenuList("Company");
        companyLink.add(new MenuLink("Company", "company"));
        companyLink.add(new MenuLink("Asset", "Asset"));
        this.links.add(companyLink);
    }

    public MenuList getLinks() {
        return links;
    }

}
