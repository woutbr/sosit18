package controller;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.FactoryFinder;
import javax.inject.Named;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.Renderer;
import net.bootsfaces.component.ComponentsEnum;

/**
 * Register the Bootsfaces renderers
 * https://beyondjava.net/running-bootsfaces-on-glassfish-or-payara
 */
@ApplicationScoped
@Named
public class InitBootsfacesBean {

    private RenderKit defaultRenderKit = null;

    public void startup(@Observes @Initialized(ApplicationScoped.class) Object context) {
        initializeBootsfacesRenderers();
    }

    private void initializeBootsfacesRenderers() {
        int componentsTotal = 0;
        int renderersAddedCount = 0;

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
                LOGGER.log(Level.INFO, "Init Bootsfaces: Skipping internal component: {0}", className);
            } else {

                LOGGER.log(Level.INFO, "Init Bootsfaces: Processing component: {0}", className);
                componentsTotal++;

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
                    LOGGER.log(Level.INFO, "Init Bootsfaces: Registering renderer: {0}/{1}", new Object[]{rendererFamily, rendererType});

                    addRenderer(rendererFamily, rendererType, renderer);
                    // ****** THIS IS THE IMPORTANT CALL TO REGISTER THE RENDERER *********
                    renderersAddedCount++;
                } catch (Throwable e) {
                    LOGGER.log(Level.WARNING, "Init Bootsfaces: Unable to register renderer for component: {0}", className);
                }
            }
        }
        LOGGER.log(Level.INFO, "Init Bootsfaces: registered renderers/total components : {0}/{1}", new Object[]{renderersAddedCount, componentsTotal});
    }

    public void addRenderer(String family, String rendererType, Renderer renderer) {
        getDefaultRenderKit().addRenderer(family, rendererType, renderer);
    }

    /*
     * From org.primefaces.mobile.renderkit.MobileRenderKit
     */
    private RenderKit getDefaultRenderKit() {
        if (defaultRenderKit == null) {
            RenderKitFactory renderKitFactory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            defaultRenderKit = renderKitFactory.getRenderKit(facesContext, RenderKitFactory.HTML_BASIC_RENDER_KIT);
        }
        return defaultRenderKit;
    }
}
