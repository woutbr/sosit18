package be.hbo5.java.xml;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * https://stackoverflow.com/a/19591302
 * @author woutbr@student.hik.be
 */
public final class XmlUtil {

    private XmlUtil() {
    }

    public static List<Node> asList(NodeList n) {
        return n.getLength() == 0
                ? Collections.<Node>emptyList() : new NodeListWrapper(n);
    }

    static final class NodeListWrapper extends AbstractList<Node> implements RandomAccess {

        private final NodeList list;

        NodeListWrapper(NodeList l) {
            list = l;
        }

        @Override
        public Node get(int index) {
            return list.item(index);
        }

        @Override
        public int size() {
            return list.getLength();
        }
    }
}
