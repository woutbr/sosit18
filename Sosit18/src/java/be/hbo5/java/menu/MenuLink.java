package be.hbo5.java.menu;

/**
 * @author woutbr@student.hik.be
 */
public class MenuLink extends MenuItem{
    private String outcome;

    public MenuLink(String name, String roles, String href) {
        super(name, roles);
        this.outcome = href;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "MenuLink{" + "name=" + getName() + ", outcome=" + outcome + ", roles=" + getRoles() + '}';
    }
}
