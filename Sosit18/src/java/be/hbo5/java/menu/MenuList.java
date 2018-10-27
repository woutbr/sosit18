package be.hbo5.java.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author woutbr@student.hik.be
 */
public class MenuList extends MenuItem implements Iterable<MenuItem> {

    private List<MenuItem> subItems;

    public MenuList(String name) {
        super(name);
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<MenuItem> subItems) {
        this.subItems = subItems;
    }

    public int size() {
        return this.subItems.size();
    }

    public void add(MenuItem subItem) {
        if (this.subItems == null) {
            this.subItems = new ArrayList<MenuItem>();
        }
        this.subItems.add(subItem);
    }

    public MenuItem get(int i) {
        return this.subItems.get(i);
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
            return get(index);
        }

    }

}
