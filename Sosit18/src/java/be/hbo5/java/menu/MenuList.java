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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MenuList)) {
            return false;
        }
        MenuList other = (MenuList) obj;
        return !((this.getName() == null && other.getName() != null) || (this.getName() != null && !this.getName().equals(other.getName())));
    }
    
    /**
     * Adds all it children which aren't a MenuList to the given List.
     * Also add all the children of sub MenuList's with recursion.
     * @param listOfItems List to which MenuItem's have to be added.
     */
    public void flattenList(List<MenuItem> listOfItems) {
        for (MenuItem mi : this) {
            if (MenuList.class.isInstance(mi)) {
                ((MenuList)mi).flattenList(listOfItems);
            }else{
                listOfItems.add(mi);
            }
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + 53;
    }

    public int size() {
        return this.children.size();
    }

    public void add(MenuItem subItem) {
        if (this.children == null) {
            this.children = new ArrayList<>();
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
