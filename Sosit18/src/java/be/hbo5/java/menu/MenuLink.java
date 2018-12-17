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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MenuLink)) {
            return false;
        }
        MenuLink other = (MenuLink) obj;
        return !((this.outcome == null && other.outcome != null) || (this.outcome != null && !this.outcome.equals(other.outcome)));
    }

    @Override
    public int hashCode() {
        return this.outcome.hashCode() + 53;
    }
}
