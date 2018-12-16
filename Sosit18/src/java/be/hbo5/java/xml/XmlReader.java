package be.hbo5.java.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * woutbr@student.hik.be
 */
public final class XmlReader {

    private XmlReader() {
    }

    public static Document readXmlFile(InputStream inputstream) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            dbFactory.setSchema(new Schema());//TODO add validator
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputstream);
            doc.getDocumentElement().normalize();
            
            return doc;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}
