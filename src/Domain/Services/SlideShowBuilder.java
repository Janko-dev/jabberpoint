package Domain.Services;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide;
import Domain.Core.SlideShow;
import Domain.Core.SlideShowComponent;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SlideShowBuilder implements DomainBuilder<SlideShow, Node>{

    private SlideShow slideShow;

    public SlideShowBuilder(){
        reset();
    }

    @Override
    public void reset() {
        this.slideShow = new SlideShow();
    }

    @Override
    public void setTitle(String title) {
        this.slideShow.title = title;
    }

    @Override
    public void setAuthor(String author) {
        this.slideShow.author = author;
    }

    @Override
    public void setDate(String date) {
        this.slideShow.date = date;
    }

    public List createList(Node n) {
        NodeList children = n.getChildNodes();
        List listComposite = new List();
        for (int i = 0, len = children.getLength(); i < len; i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            listComposite.components.add(createItem(children.item(i)));
        }
        return listComposite;
    }

    public Table createTable(Node n) {
        NamedNodeMap attrs = n.getAttributes();
        int rows = Integer.parseInt(attrs.getNamedItem("rows").getTextContent());
        int cols = Integer.parseInt(attrs.getNamedItem("cols").getTextContent());

        Table tableComposite = new Table(rows, cols);
        NodeList children = n.getChildNodes();
        for (int i = 0, len = children.getLength(); i < len; i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            tableComposite.components.add(createItem(children.item(i)));
        }
        return tableComposite;
    }

    public SlideShowComponent createItem(Node n){
        String typeName = n.getNodeName();
        switch (typeName) {
            case "text":
                return new TextItem(n.getTextContent());
            case "list": {
                return createList(n);
            }
            case "image":
                String src = n.getAttributes().getNamedItem("src").getTextContent();
                return new ImageItem(src);
            case "table": {
                return createTable(n);
            }
            default:
                throw new RuntimeException("Unreachable, expected node type");
        }
    }

    public Slide createSlide(Node xmlSlide){

        Slide newSlide = new Slide();
        NodeList items = xmlSlide.getChildNodes();

        for (int i = 0, len = items.getLength(); i < len; i++){
            Node n = items.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) continue;
            newSlide.components.add(createItem(n));
        }
        return newSlide;
    }

    @Override
    public void appendSlide(Node xmlSlide) {
        this.slideShow.components.add(createSlide(xmlSlide));
    }

    @Override
    public SlideShow getResult() {
        return this.slideShow;
    }
}
