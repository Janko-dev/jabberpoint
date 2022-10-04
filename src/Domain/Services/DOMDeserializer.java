package Domain.Services;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide;
import Domain.Core.SlideShowComponent;
import Utils.ErrorUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMDeserializer implements Deserializer {

    private Document document;
    private NodeList slides;

    public DOMDeserializer(Document document){
        this.document = document;
        this.slides = document.getElementsByTagName("slide");
    }

    @Override
    public Slide convertToSlide(int nodeIndex) {
        Slide newSlide = new Slide();
        Node DOMslide = slides.item(nodeIndex);
        if (DOMslide.getNodeType() != Node.ELEMENT_NODE) return null;
        NodeList items = DOMslide.getChildNodes();

        for (int i = 0, len = items.getLength(); i < len; i++){
            Node current = items.item(i);
            if (current.getNodeType() != Node.ELEMENT_NODE) continue;
            newSlide.components.add(convertToItem(current));
        }
        return newSlide;
    }

    @Override
    public String parseTitle() {
        return document.getElementsByTagName("title").item(0).getTextContent();
    }

    @Override
    public String parseAuthor() {
        return document.getElementsByTagName("author").item(0).getTextContent();
    }

    @Override
    public String parseDate() {
        return document.getElementsByTagName("date").item(0).getTextContent();
    }

    @Override
    public int getSlidesLength() {
        return document.getElementsByTagName("slide").getLength();
    }

    private SlideShowComponent convertToItem(Node node) {
        String typeName = node.getNodeName();
        switch (typeName) {
            case "text":
                return new TextItem(node.getTextContent());
            case "list":
                return convertToList(node);
            case "image":
                String src = node.getAttributes().getNamedItem("src").getTextContent();
                return new ImageItem(src);
            case "table":
                return convertToTable(node);
            default:
                throw new RuntimeException("Unreachable, expected specific node type");
        }
    }

    private Table convertToTable(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        int rows = Integer.parseInt(attrs.getNamedItem("rows").getTextContent());
        int cols = Integer.parseInt(attrs.getNamedItem("cols").getTextContent());

        Table tableComposite = new Table(rows, cols);
        NodeList rowNodes = node.getChildNodes();
        for (int i = 0, rowLen = rowNodes.getLength(); i < rowLen; i++) {
            if (rowNodes.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
//            List tableRow = convertToList(children.item(i));
//            ErrorUtils.assertEquals(tableRow.components.size() == cols,
//                    "Row elements must match specified columns in XML");
//            tableComposite.components.add(tableRow);

            NodeList colNodes = rowNodes.item(i).getChildNodes();
            for (int j = 0, colLen = colNodes.getLength(); j < colLen; j++){
                if (colNodes.item(j).getNodeType() != Node.ELEMENT_NODE) continue;
                tableComposite.components.add(convertToItem(colNodes.item(j)));
            }
        }
        ErrorUtils.assertEquals(tableComposite.components.size() == rows*cols,
                "Table rows and columns must match specified rows and cols in XML");
        return tableComposite;
    }

    private List convertToList(Node node) {
        NodeList children = node.getChildNodes();
        List listComposite = new List();
        for (int i = 0, len = children.getLength(); i < len; i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            listComposite.components.add(convertToItem(children.item(i)));
        }
        return listComposite;
    }
}
