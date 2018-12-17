package be.hbo5.java.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * woutbr@student.hik.be
 */
public final class XmlReader {

    private XmlReader() {
    }

    /**
     * Read an InputStream, validate it with the given dtd and return a Document
     * @param xmlStream XML stream
     * @param dtdStream DTD stream for the given xmlStream
     * @return Document from the given
     * @throws java.io.IOException If any IO errors occur
     * @throws org.xml.sax.SAXException If any parse errors occur
     */
    public static Document readXmlFile(InputStream xmlStream, InputStream dtdStream) throws SAXException, IOException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //Catch the request for the dtd file, 
            //since it will not find it in a jar
            dBuilder.setEntityResolver((String publicId, String systemId) -> {
                if (systemId.contains(".dtd")) {
                    return new InputSource(dtdStream);
                } else {
                    return null;
                }
            });
            Document doc = dBuilder.parse(xmlStream);
            doc.getDocumentElement().normalize();

            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
