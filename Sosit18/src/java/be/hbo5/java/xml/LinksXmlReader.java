package be.hbo5.java.xml;

import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import java.io.InputStream;
import java.net.URI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author woutbr@student.hik.be
 */
public final class LinksXmlReader {

    private static final String XML_ATTRIBUTE_NAME = "name";
    private static final String XML_ATTRIBUTE_ROLES = "roles";
    private static final String XML_ATTRIBUTE_LINK = "outcome";

    private LinksXmlReader() {
    }

    public static MenuList readLinksFromXmlFile(InputStream inputstream) {
        Document doc = XmlReader.readXmlFile(inputstream);
        Node root = doc.getDocumentElement();
        MenuList menu = createMenuListFromNode(root);
        return menu;
    }

    private static MenuLink createMenuLinkFromNode(Node linkNode) {
        NamedNodeMap nodeAttr = linkNode.getAttributes();
        MenuLink menuLink = new MenuLink(getAttributeValue(nodeAttr, XML_ATTRIBUTE_NAME),
                getAttributeValue(nodeAttr, XML_ATTRIBUTE_ROLES),
                getAttributeValue(nodeAttr, XML_ATTRIBUTE_LINK));
        return menuLink;
    }

    private static MenuList createMenuListFromNode(Node listNode) {
        NamedNodeMap nodeAttr = listNode.getAttributes();
        MenuList menuList = new MenuList(getAttributeValue(nodeAttr, XML_ATTRIBUTE_NAME),
                getAttributeValue(nodeAttr, XML_ATTRIBUTE_ROLES));
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

    private static String getAttributeValue(NamedNodeMap attributes, String name) {
        Node attr = attributes.getNamedItem(name);
        String value = "";
        if (attr != null) {
            value = attr.getNodeValue();
        }
        return value;
    }
}
