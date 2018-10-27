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
        
        this.links.add(new MenuLink("Index", "index"));
        
        MenuList ticketList = new MenuList("Ticket ");
        ticketList.add(new MenuLink("All tickets", ""));
        ticketList.add(new MenuLink("New ticket", "tickets"));
        ticketList.add(new MenuLink("Ticket type", "tickettype"));
        ticketList.add(new MenuLink("Ticket status", "ticketstatus"));
        ticketList.add(new MenuLink("Ticket severity", "ticketseverity"));
        ticketList.add(new MenuLink("Ticket priority", "ticketpriority"));
        ticketList.add(new MenuLink("Assign new action", "Action"));
        this.links.add(ticketList);

        MenuList companyLink = new MenuList("Companies ");
        companyLink.add(new MenuLink("All companies", ""));
        companyLink.add(new MenuLink("New company", "company"));
        this.links.add(companyLink);

        MenuList assetLink = new MenuList("Asset ");
        assetLink.add(new MenuLink("All assets", "Asset"));
        assetLink.add(new MenuLink("New asset", ""));
        assetLink.add(new MenuLink("Asset group", "assetgroup"));
        assetLink.add(new MenuLink("Asset location", "assetlocation"));
        this.links.add(assetLink);

        MenuList userLink = new MenuList("User ");
        userLink.add(new MenuLink("All users", ""));
        userLink.add(new MenuLink("New User", "useraccount"));
        userLink.add(new MenuLink("User role", "role"));
        this.links.add(userLink);
        
        this.links.add(new MenuLink("SLA", "sla"));
        this.links.add(new MenuLink("Contact", ""));
    }

    public MenuList getLinks() {
        return links;
    }

}
