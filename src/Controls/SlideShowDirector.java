package Controls;

import Domain.Core.SlideShow;
import Domain.Services.DomainBuilder;
import Domain.Services.SlideShowBuilder;
import Infrastructure.Reader;
import Infrastructure.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SlideShowDirector extends DomainDirector<SlideShow, Node>{

    public SlideShowDirector(DomainBuilder<SlideShow, Node> builder){
        this.changeBuilder(builder);
    }

    @Override
    public SlideShow make() {
        Reader<Document> reader = new XMLReader();
        Document doc = reader.readFile("resources/slideshow_test.xml");

        builder.reset();

        builder.setTitle(doc.getElementsByTagName("title").item(0).getTextContent());
        builder.setAuthor(doc.getElementsByTagName("author").item(0).getTextContent());
        builder.setDate(doc.getElementsByTagName("date").item(0).getTextContent());

        NodeList slides = doc.getElementsByTagName("slide");
        for (int i = 0, len = slides.getLength(); i < len; i++){

            Node slideNode = slides.item(i);
            if (slideNode.getNodeType() == Node.ELEMENT_NODE){
                builder.appendSlide(slideNode);
            }
        }
        return builder.getResult();
    }
}
