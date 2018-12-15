package be.hbo5.java.menu;

/**
 * @author woutbr@student.hik.be
 */
public class MenuLink extends MenuItem{
    private String href;

    public MenuLink(String name, String href) {
        super(name);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "MenuLink{" + "name=" + getName() + ", href=" + href + '}';
    }
}
