package be.hbo5.java.menu;

import com.sun.el.ValueExpressionLiteral;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.FacesComponent;
import net.bootsfaces.component.dropMenu.DropMenu;

/**
 * @author woutbr@student.hik.be
 */
@FacesComponent(createTag = true, tagName = "menuList", namespace = "https://github.com/woutbr/sosit18")
public class MenuList extends DropMenu implements Iterable<MenuItem> {

    private List<MenuItem> subItems;

    public MenuList(String name) {
        this.setValueExpression("value", new ValueExpressionLiteral(name, String.class));
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
