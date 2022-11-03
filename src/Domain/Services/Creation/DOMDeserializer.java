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

/**
 * A concrete class that implements the deserializer interface and is able to deserialize a Document Object Model (DOM) to domain specific components.
 * This deserializer depends on the {@code org.w3c.dom} package.
 * The DOMDeserializer recursively walks the DOM nodes to convert each valid node.
 */
public class DOMDeserializer implements Deserializer {

    private final Document document;
    private final NodeList slides;
    private TreeMap<Integer, String> subjectMap = null;

    /**
     * Constructor that sets the document property to the supplied {@code Document}
     * and sets the slides property to the {@code NodeList} obtained by getting elements with the "slide" tag.
     * @param document an instance of {@code org.w3c.dom.Document}
     */
    public DOMDeserializer(Document document){
        this.document = document;
        this.slides = document.getElementsByTagName("slide");
    }

    /**
     * One-time only computation of caching the subjects in the DOM slides.
     * A treemap is used to store the index of a slide with a subject as the key and the subject itself as the value.
     * The caching computation ensures that no subject is stored twice, if they follow each other in the list of DOM nodes.
     */
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

    /**
     * Create a Table of Contents slide by caching the subjects, and finding the subject to be highlighted.
     * A standard structure and styling is used, with a TOC header and a list of text items.
     * @param nodeIndex The current index in the list of DOM nodes.
     *                  Used to find the subject after the current node index, for highlighting purposes.
     * @return an instance of {@code TOCSlide}
     */
    public TOCSlide convertToTOC(int nodeIndex){
        cacheSubjects();
        TOCSlide tocSlide = new TOCSlide();
        Map.Entry<Integer, String> foundEntry = subjectMap.ceilingEntry(nodeIndex);
        List tocList = new List();
        TextItem header = new TextItem("Table of Contents");
        header.addStyle(new FontStyle("Arial", 38));
        tocList.addComponent(header);
        for (Map.Entry<Integer, String> entry : subjectMap.entrySet()) {
            TextItem textItem = new TextItem(String.format("%s %s", entry.getKey() + 1, entry.getValue()));
            textItem.addStyle(new FontStyle("Arial", 28));

            if (foundEntry != null && foundEntry.equals(entry)) {
                textItem.addStyle(new ColorStyle(255, 0, 0));
            }
            tocList.addComponent(textItem);
        }
        tocSlide.addComponent(tocList);
        return tocSlide;
    }

    /**
     * Create a Slide that can either be a {@code RegularSlide} or a {@code TOCSlide}.
     * Depending on the type attribute of the current node being of type "toc", creation functionality is delegated to {@code convertToTOC(int nodeIndex)}
     * Otherwise, a regular slide is created by converting each of its DOM children, and parsing the attributes of the slide DOM element to styling elements.
     * @param nodeIndex The current index in the list of DOM nodes.
     * @return Slide that can either be a {@code RegularSlide} or a {@code TOCSlide}
     */
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

    /**
     * @return the content of the title tag of the DOM as a String
     */
    @Override
    public String parseTitle() {
        return document.getElementsByTagName("title").item(0).getTextContent();
    }

    /**
     * @return the content of the author tag of the DOM as a String
     */
    @Override
    public String parseAuthor() {
        return document.getElementsByTagName("author").item(0).getTextContent();
    }

    /**
     * @return the content of the date tag of the DOM as a String
     */
    @Override
    public String parseDate() {
        return document.getElementsByTagName("date").item(0).getTextContent();
    }

    /**
     * @return The amount of slide elements in the DOM.
     */
    @Override
    public int getSlidesLength() {
        return document.getElementsByTagName("slide").getLength();
    }

    /**
     * Switches over the DOM element tags and delegates creation functionality to appropriate methods.
     * The tags are either "text", "list", "image", or "table".
     * @param node The {@code org.w3c.dom.Node} object to match
     * @return an instance of a {@code SlideShowComponent}.
     * @exception RuntimeException thrown when node is not one of the specified tag types, i.e., unreachable in the normal case.
     */
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

    /**
     * Create a text item.
     * the assumption is made that the node parameter is of type "text",
     * because this method is called from {@code convertToItem(Node)}.
     * Therefore, no assertion is made.
     * Every possible style is attempted to parse and append to the {@code TextItem}.
     * @param node The {@code org.w3c.dom.Node} object to parse
     * @return an instance of a {@code TextItem}.
     */
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

    /**
     * Convert a String of structure {@code "RED, GREEN, BLUE"} to an integer array of {@code int[] { RED, GREEN, BLUE }},
     * where RED, GREEN, and BLUE are parsed to integers.
     * @param colorString A valid string of the form {@code "RED, GREEN, BLUE"} where RED, GREEN, and BLUE are numeric string literals.
     * @return an Integer array of length 3 with the string parsed into 3 color components.
     * @exception NumberFormatException thrown when either of the color components is not able to be parsed to an Integer.
     */
    private int[] convertToRGB(String colorString){
        String[] colorParts = colorString.split(",");
        ErrorUtils.assertEquals(colorParts.length == 3, "Color components must be in \"R,G,B\" form");
        int red = Integer.parseInt(colorParts[0].trim());
        int green = Integer.parseInt(colorParts[1].trim());
        int blue = Integer.parseInt(colorParts[2].trim());
        return new int[] { red, green, blue };
    }

    /**
     * Create an image item.
     * the assumption is made that the node parameter is of type "image",
     * because this method is called from {@code convertToItem(Node)}.
     * Therefore, no assertion is made.
     * the width and height attributes of the image DOM node are parsed to integers.
     * @param node The {@code org.w3c.dom.Node} object to parse
     * @return an instance of a {@code ImageItem}.
     * @exception NumberFormatException thrown when the width of height are not able to be parsed to Integers.
     */
    private ImageItem convertToImageItem(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        String src = attrs.getNamedItem("src").getTextContent();
        int width = Integer.parseInt(attrs.getNamedItem("width").getTextContent());
        int height = Integer.parseInt(attrs.getNamedItem("height").getTextContent());
        return new ImageItem(src, width, height);
    }

    /**
     * Create a Table composite.
     * the assumption is made that the node parameter is of type "table",
     * because this method is called from {@code convertToItem(Node)}.
     * Therefore, no assertion is made.
     * the rows and cols attributes of the table DOM node are parsed to integers.
     * @param node The {@code org.w3c.dom.Node} object to parse
     * @return an instance of a {@code Table}.
     * @exception NumberFormatException thrown when the rows of cols are not able to be parsed to Integers.
     * @exception RuntimeException thrown when either rows or cols don't match the size of the table elements.
     */
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

    /**
     * Create a List composite.
     * the assumption is made that the node parameter is of type "list",
     * because this method is called from {@code convertToItem(Node)}.
     * Therefore, no assertion is made.
     * Every possible style is attempted to parse and append to the {@code List}.
     * Thereafter, the child elements of the node are iterated through and converted.
     * @param node The {@code org.w3c.dom.Node} object to parse
     * @return an instance of a {@code List}.
     */
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
