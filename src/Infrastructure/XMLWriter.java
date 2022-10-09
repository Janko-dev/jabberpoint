package Infrastructure;

import Domain.Core.SlideShow;
import Domain.Services.Visitors.XMLSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter extends Writer{

    private static final String META_DTD =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE slideshow[\n" +
            "        <!ELEMENT slideshow (title, author, date, slide+)>\n" +
            "        <!ELEMENT title (#PCDATA)>\n" +
            "        <!ELEMENT author (#PCDATA)>\n" +
            "        <!ELEMENT date (#PCDATA)>\n" +
            "        <!ELEMENT slide (text|image|list|table)*>\n" +
            "        <!ELEMENT text (#PCDATA)>\n" +
            "        <!ELEMENT image EMPTY>\n" +
            "        <!ATTLIST image src CDATA #REQUIRED>\n" +
            "        <!ELEMENT list (text|image|list|table)*>\n" +
            "        <!ELEMENT table (row)*>\n" +
            "        <!ELEMENT row (text|image|list|table)*>\n" +
            "        <!ATTLIST table rows CDATA #REQUIRED>\n" +
            "        <!ATTLIST table cols CDATA #REQUIRED>\n" +
            "        ]>\n";

    private XMLSerializer xmlSerializer;

    public XMLWriter(){
        xmlSerializer = new XMLSerializer();
    }

    @Override
    public void writeToFile(String filePath, SlideShow slideShow) {
        writeToFile(new File(filePath), slideShow);
    }

    @Override
    public void writeToFile(File file, SlideShow slideShow) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            xmlSerializer.setBufferedWriter(writer);
            writer.write(META_DTD);
            slideShow.accept(xmlSerializer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
