package Infrastructure;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Slide;
import Domain.Core.SlideShow;
import Domain.Services.XMLSerializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter extends Writer{

    private static final String DTD =
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
            "        <!ELEMENT table (list)*>\n" +
            "        <!ATTLIST table rows CDATA #REQUIRED>\n" +
            "        <!ATTLIST table cols CDATA #REQUIRED>\n" +
            "        ]>\n";

    private XMLSerializer xmlSerializer;

    public XMLWriter(){
        xmlSerializer = new XMLSerializer();
    }


    @Override
    public void writeToFile(String filePath, SlideShow slideShow) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        xmlSerializer.setBufferedWriter(writer);
        writer.write(DTD);
        writer.write("<slideshow>");
        for (Iterator<Slide> iter = slideShow.createIterator(); !iter.isDone(); iter.next()) {
            xmlSerializer.visitSlide(iter.current());
        }
        writer.write("</slideshow>");
    }

}
