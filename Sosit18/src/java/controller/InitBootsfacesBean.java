package controller;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponentBase;
import javax.faces.render.Renderer;
import net.bootsfaces.component.ComponentsEnum;

@ManagedBean(eager=true)
@ApplicationScoped
public class InitBootsfacesBean {
    /**
     * Register the Bootsfaces renderers
     * https://beyondjava.net/running-bootsfaces-on-glassfish-or-payara
     */
    private void initializeBootsfacesRenderers() {
        
        // Loop through all of the Bootsfaces components
        for (ComponentsEnum value : ComponentsEnum.values()) {
            
            // Get the component class information
            String className;
            
            // switchComponent has wrong classpath in ComponentsEnum
            if (value.name().equals("switchComponent")) {
                // Use correct qualified name
                className = "net.bootsfaces.component.switchComponent.Switch";
            } else {
                // Otherwise, use specified value
                className = value.classname();
            }
            
            // See if this is an internal reference
            if (className.contains("Internal")) {
                LOGGER.log(Level.INFO, "Init Bootsfaces: Skipping internal component: " + className);
            } else {
                
                LOGGER.log(Level.INFO, "Init Bootsfaces: Processing component: " + className);
                
                try {
                     // See if we can instantiate the class
                    Class componentClass = Class.forName(className);
                    Class<UIComponentBase> baseClass = componentClass.asSubclass(UIComponentBase.class);
                    UIComponentBase component = baseClass.newInstance();
                    String rendererFamily = component.getFamily();
                    String rendererType = component.getRendererType();

                    // Determine the renderer class name
                    String simpleName = componentClass.getSimpleName();
                    String rendererClassName;
                    switch (simpleName) {
                        case "NavCommandLink":
                            // Shares same renderer with NavLink
                            rendererClassName = "net.bootsfaces.component.navLink.NavLinkRenderer";
                            break;
                        default:
                            // We have to guess at the name of the renderer
                            rendererClassName = className + "Renderer";
                    }
                    
                    // Look up the renderer
                    Class rendererSuperclass = Class.forName(rendererClassName);
                    Class<Renderer> rendererClass = rendererSuperclass.asSubclass(Renderer.class);
                    Renderer renderer = rendererClass.newInstance();
                    LOGGER.log(Level.INFO, "Init Bootsfaces: Registering renderer: " + rendererFamily + "/" + rendererType);

                    // ****** THIS IS THE IMPORTANT CALL TO REGISTER THE RENDERER *********
                    addRenderer(rendererFamily, rendererType, renderer);
                } catch (Throwable e) {
                    LOGGER.log(Level.WARNING, "Init Bootsfaces: Unable to register renderer for component: " + className, e);
                }
           }
        }
    }

    public void addRenderer(String family, String rendererType, Renderer renderer) {
        getDefaultRenderKit().addRenderer(family, rendererType, renderer);
    }
}