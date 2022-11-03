package Infrastructure;

import Domain.Services.Creation.DOMDeserializer;
import Domain.Services.Creation.Deserializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Concrete XML reader class.
 * Extends abstract base Reader class
 */
public class XMLReader extends Reader {

    /**
     * Calls {@code readFile(File)} method by converting param filePath to {@code java.io.File} object.
     * @param filePath file path is provided as a String.
     * @return {@code Deserializer} object
     */
    @Override
    public Deserializer readFile(String filePath) {
        return readFile(new File(filePath));
    }

    /**
     * Builds a document builder that parses and generates a document object model of the provided xml file.
     * Encapsulates the document object in a {@code DOMDeserializer} object which implements {@code Deserializer}, and returns it.
     * @param file a {@code java.io.File} object is provided.
     * @return {@code DOMDeserializer} object that encapsulates the parsed xml as a Document object model.
     *  ParserConfigurationException thrown when document builder fails to parse xml file.
     *  IOException thrown when any IO errors occur.
     *  SAXException thrown when any parse errors occur.
     */
    @Override
    public Deserializer readFile(File file) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc =  builder.parse(file);
            doc.getDocumentElement().normalize();
            return new DOMDeserializer(doc);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
