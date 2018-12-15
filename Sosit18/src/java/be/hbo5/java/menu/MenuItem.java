package be.hbo5.java.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author woutbr@student.hik.be
 */
public abstract class MenuItem implements Serializable{

    private String name;
    private List<String> roles;

    public MenuItem(String name, String roles) {
        this.name = name;
        if (roles.isEmpty()) {
            this.roles = new ArrayList<>();
        } else {
            this.roles = Arrays.asList(roles.split("\\s+"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean hasRole(String rolename) {
        return this.roles.contains(name);
    }

    @Override
    public String toString() {
        return "MenuItem{" + "name=" + name + ", roles=" + getRoles() + '}';
    }

}
