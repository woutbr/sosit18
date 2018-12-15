package be.hbo5.java.xml;

import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import net.bootsfaces.component.navLink.NavLink;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author woutbr@student.hik.be
 */
public final class LinksXmlReader {
    
    private LinksXmlReader() {
    }
    
    public static MenuList readLinksFromXmlFile(String pathname) {
        Document doc = XmlReader.readXmlFile(pathname);
        Node root = doc.getDocumentElement();
        MenuList menu = createMenuListFromNode(root);
        return menu;
    }
    
    private static MenuLink createMenuLinkFromNode(Node linkNode) {
        NamedNodeMap nodeAttr = linkNode.getAttributes();
        MenuLink menuLink = new MenuLink(nodeAttr.getNamedItem("name").getNodeValue(),
                nodeAttr.getNamedItem("href").getNodeValue());
        //TODO NavLink return ipv MenuLink
        NavLink navLink = new NavLink();
        navLink.setValue(nodeAttr.getNamedItem("name").getNodeValue());
        navLink.setHref(nodeAttr.getNamedItem("href").getNodeValue());
        return menuLink;
    }
    
    private static MenuList createMenuListFromNode(Node listNode) {
        MenuList menuList = new MenuList(listNode.getAttributes().getNamedItem("name").getNodeValue());
        for (Node n : XmlUtil.asList(listNode.getChildNodes())) {
            switch (n.getNodeName()) {
                case "link":
                    menuList.add(createMenuLinkFromNode(n));
                    break;
                case "list":
                    menuList.add(createMenuListFromNode(n));
                    break;
            }
        }
        return menuList;
    }
}
