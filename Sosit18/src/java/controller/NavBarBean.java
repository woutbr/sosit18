package controller;

//import be.hbo5.java.menu.HeaderNavBarLinks;
import be.hbo5.java.menu.MenuItem;
import be.hbo5.java.menu.MenuList;
import be.hbo5.java.xml.LinksXmlReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.xml.sax.SAXException;

/**
 * Deze bean laad de links voor de navbar uit een xml bestand.
 * @author woutbr@student.hik.be
 */
@Named(value = "navBarBean")
@SessionScoped
public class NavBarBean implements Serializable {

    @Inject
    private AuthBean authBean;

    private final MenuList links;
    private List<MenuItem> listOfMenuItems;

    public NavBarBean() throws SAXException, IOException {
        //DEBUG Print the files on the classpath
//        System.out.println("getResourceFiles: "+getResourceFiles("/"));

        try {
            //Get the xml file from the jar.
            //Where to place and how to read configuration resource files in servlet based application?
            //https://stackoverflow.com/a/2161583 : answer BalusC
            String pathNavbarlinks = "/resources/navbarlinks";
            InputStream navbarlinksXmlStream = getResourceAsStream(pathNavbarlinks + ".xml");
            InputStream navbarlinksDtdStream = getResourceAsStream(pathNavbarlinks + ".dtd");

            this.links = LinksXmlReader.readLinksFromXmlFile(navbarlinksXmlStream, navbarlinksDtdStream);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(NavBarBean.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public MenuList getLinks() {
        return this.links;
    }
    
    /**
     * Returns all the MenuLink's in links as a List.
     */
    public List<MenuItem> getLinksAsList(){
        if(this.listOfMenuItems == null){
            List<MenuItem> list = new ArrayList<>();
            this.links.flattenList(list);
            this.listOfMenuItems = list;
        }
        return this.listOfMenuItems;
    }

    /**
     * Can the given MenuItem be displayed for the current user.
     * @param menuitem MenuItem for which to check
     * @return true if in debugMode, the given menuitem has no roles 
     * or it has a role equal to the roles of the current user
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
     * Get a list of resources from classpath directory
     * https://stackoverflow.com/a/3923685 : answer iirekm
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

    /**
     * Returns an input stream for reading the specified resource. First tries
     * to find on ClassLoader. If not found try from this class.
     * @param resource The resource name
     * @return An InputStream object 
     * or null if no resource with this name is found
     */
    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
