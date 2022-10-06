package Infrastructure;

import Domain.Services.DOMDeserializer;
import Domain.Services.Deserializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReader extends Reader {

    @Override
    public Deserializer readFile(String filePath) {
        return readFile(new File(filePath));
    }

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
