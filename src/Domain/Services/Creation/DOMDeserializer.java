package Domain.Services.Creation;

import Domain.Core.Slide.RegularSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide.Slide;
import Domain.Core.SlideShowComponent;
import Domain.Core.Style.*;
import Domain.Core.Slide.TOCSlide;
import Utils.ErrorUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;
import java.util.TreeMap;

public class DOMDeserializer implements Deserializer {

    private final Document document;
    private final NodeList slides;
    private TreeMap<Integer, String> subjectMap = null;

    public DOMDeserializer(Document document){
        this.document = document;
        this.slides = document.getElementsByTagName("slide");
    }

    private void cacheSubjects(){
        if (subjectMap != null) return;
        subjectMap = new TreeMap<>();
        String prevSubject = "";
        for (int index = 0, len = getSlidesLength(); index < len; index++) {
            Node DOMslide = slides.item(index);
            NamedNodeMap attrs = DOMslide.getAttributes();
            if (attrs.getNamedItem("subject") != null && !prevSubject.equals(attrs.getNamedItem("subject").getTextContent())) {
                String subject = attrs.getNamedItem("subject").getTextContent();
                subjectMap.put(index, subject);
                prevSubject = subject;
            }
        }
    }

    public TOCSlide convertToTOC(int nodeIndex){
        cacheSubjects();
        TOCSlide tocSlide = new TOCSlide();
        Map.Entry<Integer, String> foundEntry = subjectMap.ceilingEntry(nodeIndex);
        List list = new List();
        TextItem header = new TextItem("Inhoudsopgave");
        header.addStyle(new FontStyle("Arial", 38));
        list.addComponent(header);
        for (Map.Entry<Integer, String> entry : subjectMap.entrySet()) {
            TextItem textItem = new TextItem(String.format("%s %s", entry.getKey() + 1, entry.getValue()));
            textItem.addStyle(new FontStyle("Arial", 28));

            if (foundEntry != null && foundEntry.equals(entry)) {
                textItem.addStyle(new ColorStyle(255, 0, 0));
            }
            list.addComponent(textItem);
        }
        tocSlide.addComponent(list);
        return tocSlide;
    }

    @Override
    public Slide convertToSlide(int nodeIndex) {
        Node DOMslide = slides.item(nodeIndex);
        if (DOMslide.getNodeType() != Node.ELEMENT_NODE) return null;

        NamedNodeMap attrs = DOMslide.getAttributes();
        if (attrs.getNamedItem("type") != null){
            String type = attrs.getNamedItem("type").getTextContent();
            if (type.equals("toc")){
                return convertToTOC(nodeIndex);
            }
        }

        RegularSlide newSlide = new RegularSlide(nodeIndex);
        if (attrs.getNamedItem("subject") != null){
            String subject = attrs.getNamedItem("subject").getTextContent();
            newSlide.setSubject(subject);
        }

        if (attrs.getNamedItem("background") != null){
            String bg = attrs.getNamedItem("background").getTextContent();
            newSlide.addStyle(new BackgroundStyle(convertToRGB(bg)));
        }

        NodeList items = DOMslide.getChildNodes();
        for (int i = 0, len = items.getLength(); i < len; i++){
            Node current = items.item(i);
            if (current.getNodeType() != Node.ELEMENT_NODE) continue;
            newSlide.addComponent(convertToItem(current));
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
                return convertToTextItem(node);
            case "list":
                return convertToList(node);
            case "image":
                return convertToImageItem(node);
            case "table":
                return convertToTable(node);
            default:
                throw new RuntimeException("Unreachable, expected specific node type");
        }
    }

    private TextItem convertToTextItem(Node node){
        NamedNodeMap attrs = node.getAttributes();
        TextItem textItem = new TextItem(node.getTextContent());
        if (attrs.getNamedItem("color") != null){
            String colorString = attrs.getNamedItem("color").getTextContent();
            ColorStyle colorStyle = new ColorStyle(convertToRGB(colorString));
            textItem.addStyle(colorStyle);
        }

        FontStyle fontStyle = new FontStyle();
        if (attrs.getNamedItem("font") != null){
            fontStyle.setFontName(attrs.getNamedItem("font").getTextContent());
        }
        if (attrs.getNamedItem("size") != null){
            fontStyle.setFontSize(Integer.parseInt(attrs.getNamedItem("size").getTextContent().trim()));
        }
        textItem.addStyle(fontStyle);
        return textItem;
    }

    private int[] convertToRGB(String colorString){
        String[] colorParts = colorString.split(",");
        ErrorUtils.assertEquals(colorParts.length == 3, "Color components must be in \"R,G,B\" form");
        int red = Integer.parseInt(colorParts[0].trim());
        int green = Integer.parseInt(colorParts[1].trim());
        int blue = Integer.parseInt(colorParts[2].trim());
        return new int[] { red, green, blue };
    }

    private ImageItem convertToImageItem(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        String src = attrs.getNamedItem("src").getTextContent();
        int width = Integer.parseInt(attrs.getNamedItem("width").getTextContent());
        int height = Integer.parseInt(attrs.getNamedItem("height").getTextContent());
        return new ImageItem(src, width, height);
    }

    private Table convertToTable(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        int rows = Integer.parseInt(attrs.getNamedItem("rows").getTextContent());
        int cols = Integer.parseInt(attrs.getNamedItem("cols").getTextContent());

        Table tableComposite = new Table(rows, cols);
        NodeList rowNodes = node.getChildNodes();
        for (int i = 0, rowLen = rowNodes.getLength(); i < rowLen; i++) {
            if (rowNodes.item(i).getNodeType() != Node.ELEMENT_NODE) continue;

            NodeList colNodes = rowNodes.item(i).getChildNodes();
            for (int j = 0, colLen = colNodes.getLength(); j < colLen; j++){
                if (colNodes.item(j).getNodeType() != Node.ELEMENT_NODE) continue;
                tableComposite.addComponent(convertToItem(colNodes.item(j)));
            }
        }
        ErrorUtils.assertEquals(tableComposite.getComponents().size() == rows*cols,
                "Table rows and columns must match specified rows and cols in XML");
        return tableComposite;
    }

    private List convertToList(Node node) {
        NodeList children = node.getChildNodes();
        List listComposite = new List();
        NamedNodeMap attrs = node.getAttributes();
        if (attrs.getNamedItem("bullet_point") != null){
            String bulletPoint = attrs.getNamedItem("bullet_point").getTextContent();
            listComposite.addStyle(new BulletPointStyle(bulletPoint));
        }

        for (int i = 0, len = children.getLength(); i < len; i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            SlideShowComponent component = convertToItem(children.item(i));
            listComposite.addComponent(component);
        }
        return listComposite;
    }
}
