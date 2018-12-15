package be.hbo5.java.menu;

import com.sun.el.ValueExpressionLiteral;
import javax.faces.component.FacesComponent;
import net.bootsfaces.component.navLink.NavLink;

/**
 * @author woutbr@student.hik.be
 */
@FacesComponent(createTag = true, tagName = "MenuLink", namespace = "https://github.com/woutbr/sosit18")
public class MenuLink extends NavLink{
    public MenuLink(String name, String href) {
        this.setValueExpression("value", new ValueExpressionLiteral(name, String.class));
        this.setHref(href);
    }
}
