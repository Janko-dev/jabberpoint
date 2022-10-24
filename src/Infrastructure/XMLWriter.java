package Infrastructure;

import Domain.Core.SlideShow;
import Domain.Services.Visitors.XMLDomainSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Concrete XML reader class.
 * Extends abstract base Reader class
 */
public class XMLWriter extends Writer{

    /**
     * Meta information for xml file.
     * Includes xml Document Type Definition, that should be updated when more types and rules are added.
     */
    private static final String META_DTD =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE slideshow[\n" +
            "<!--SLIDESHOW AND META DATA-->\n" +
            "        <!ELEMENT slideshow (title, author, date, (slide)+)>\n" +
            "        <!ELEMENT title (#PCDATA)>\n" +
            "        <!ELEMENT author (#PCDATA)>\n" +
            "        <!ELEMENT date (#PCDATA)>\n" +
            "<!--SLIDE-->\n" +
            "        <!ELEMENT slide (text|image|list|table)*>\n" +
            "        <!ATTLIST slide background CDATA #IMPLIED>\n" +
            "        <!ATTLIST slide type CDATA #IMPLIED>\n" +
            "        <!ATTLIST slide subject CDATA #IMPLIED>\n" +
            "<!--TEXT ITEM-->\n" +
            "        <!ELEMENT text (#PCDATA)>\n" +
            "        <!ATTLIST text color CDATA #IMPLIED>\n" +
            "        <!ATTLIST text font CDATA #IMPLIED>\n" +
            "        <!ATTLIST text size CDATA #IMPLIED>\n" +
            "<!--IMAGE ITEM-->\n" +
            "        <!ELEMENT image EMPTY>\n" +
            "        <!ATTLIST image src CDATA #REQUIRED>\n" +
            "        <!ATTLIST image width CDATA #REQUIRED>\n" +
            "        <!ATTLIST image height CDATA #REQUIRED>\n" +
            "<!--LIST ITEM-->\n" +
            "        <!ELEMENT list (text|image|list|table)*>\n" +
            "        <!ATTLIST list bullet_point CDATA #IMPLIED>\n" +
            "<!--TABLE ITEM-->\n" +
            "        <!ELEMENT table (row)*>\n" +
            "        <!ELEMENT row (text|image|list|table)*>\n" +
            "        <!ATTLIST table rows CDATA #REQUIRED>\n" +
            "        <!ATTLIST table cols CDATA #REQUIRED>\n" +
            "        ]>";

    private final XMLDomainSerializer xmlDomainSerializer;

    /**
     * Constructor instantiates a xml domain serializer object that is necessary to traverse the domain and serialize domain data to a xml format file.
     */
    public XMLWriter(){
        xmlDomainSerializer = new XMLDomainSerializer();
    }

    /**
     * Calls {@code writeToFile(String, SlideShow)} to write the slideshow object to the file path specified in the String param.
     * @param filePath  file path as a string
     * @param slideShow the slideshow object
     */
    @Override
    public void writeToFile(String filePath, SlideShow slideShow) {
        writeToFile(new File(filePath), slideShow);
    }

    /**
     * Creates a buffered writer with the provided file object, writes the {@code META_DTD} data, and traverses the slideshow using a {@code Visitor}
     * @param file      file as a {@code java.io.File} object
     * @param slideShow the slideshow object
     * @exception IOException thrown when any IO errors occur during writing.
     */
    @Override
    public void writeToFile(File file, SlideShow slideShow) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            xmlDomainSerializer.setBufferedWriter(writer);
            writer.write(META_DTD);
            slideShow.accept(xmlDomainSerializer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
