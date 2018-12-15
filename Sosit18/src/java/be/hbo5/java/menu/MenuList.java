package be.hbo5.java.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author woutbr@student.hik.be
 */
public class MenuList extends MenuItem implements Iterable<MenuItem> {

    private List<MenuItem> children;

    public MenuList(String name, String roles) {
        super(name, roles);
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "MenuList{" + "name=" + getName() + ", roles=" + getRoles() + ", children.size=" + children.size() + '}';
    }

    public int size() {
        return this.children.size();
    }

    public void add(MenuItem subItem) {
        if (this.children == null) {
            this.children = new ArrayList<MenuItem>();
        }
        this.children.add(subItem);
    }

    public MenuItem get(int i) {
        return this.children.get(i);
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new MenuIterator();
    }

    class MenuIterator implements Iterator<MenuItem> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public MenuItem next() {
            int i = index;
            index++;
            return get(i);
        }

    }

}
