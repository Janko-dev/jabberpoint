package Domain.Services.Visitors;

import Domain.Core.Slide.RegularSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideShow;
import Domain.Core.Style.Style;
import Domain.Core.Slide.TOCSlide;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Concrete xml serialization class that is responsible for appropriately writing domain data to a file.
 * This class implements the {@code DomainVisitor}, which means that a method for every kind of concrete domain class is implemented.
 */
public class XMLDomainSerializer implements DomainVisitor {

    private int indent = 1;
    private BufferedWriter writer;
    private XMLStyleSerializer xmlStyleSerializer = new XMLStyleSerializer();

    /**
     * Set the current {@code BufferedWriter} object to a given {@code BufferedWriter}.
     * The contractual methods rely on using a {@code BufferedWriter} object, so this method needs to be called before any of the visit methods is called.
     * @param writer an instance of the {@code BufferedWriter} class
     */
    public void setBufferedWriter(BufferedWriter writer){
        this.writer = writer;
        xmlStyleSerializer.setBufferedWriter(writer);
    }

    /**
     * Writes {@code indent} * {@code \t} to the current {@code BufferedWriter}.
     * This is used for formatting purposes.
     * @throws IOException thrown when any IO errors occur.
     */
    private void writeIndentation() throws IOException {
        for (int i = 0; i < indent; i++)
            writer.write("\t");
    }

    /**
     * Writes the {@code SlideShow} with a {@code BufferedWriter} to a file.
     * This method writes the slide show metadata first, i.e., title, author, and data, and then visits each slide with this {@code XMLDomainSerializer}.
     * @param slideShow an instance of SlideShow
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitSlideShow(SlideShow slideShow) {
        try {
            writer.write("<slideshow>\n");
            writer.write("\t<title>" + slideShow.getTitle() + "</title>\n");
            writer.write("\t<author>" + slideShow.getAuthor() + "</author>\n");
            writer.write("\t<date>" + slideShow.getAuthor() + "</date>\n");
            for (Iterator iter = slideShow.createIterator(); !iter.isDone(); iter.next()) {
                iter.current().accept(this);
            }
            writer.write("</slideshow>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code RegularSlide} with a {@code BufferedWriter} to a file.
     * This method writes the slide header by iterating through the list of styles and visiting each of them with the {@code XMLStyleSerializer}.
     * Then, each item of the slide is visited with this {@code XMLDomainSerializer}.
     * @param slide an instance of RegularSlide
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitConcreteSlide(RegularSlide slide) {
        try {
            writer.write("\t<slide");
            for (Style style : slide.getStyles()){
                style.accept(xmlStyleSerializer);
            }
            writer.write(">\n");
            indent++;
            for (Iterator iter = slide.createIterator(); !iter.isDone(); iter.next()) {
                iter.current().accept(this);
            }
            indent--;
            writer.write("\t</slide>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code TOCSlide} with a {@code BufferedWriter} to a file.
     * Simply writes a slide element with an attribute {@code type="toc"}.
     * @param slide an instance of TOCSlide
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitTOCSlide(TOCSlide slide) {
        try {
            writer.write("\t<slide type=\"toc\"/>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code TextItem} with a {@code BufferedWriter} to a file.
     * This method writes the text header by iterating through the list of styles and visiting each of them with the {@code XMLStyleSerializer}.
     * Thereafter, the text content stored in the text item is written to the {@code BufferedWriter}.
     * @param textItem an instance of TextItem
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitTextItem(TextItem textItem) {
        try {
            writeIndentation();
            writer.write("<text");
            for (Style style : textItem.getStyles()){
                style.accept(xmlStyleSerializer);
            }
            writer.write(">" + textItem.getText() + "</text>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code ImageItem} with a {@code BufferedWriter} to a file.
     * The image data is written as required xml attributes within an {@code <image>} tag.
     * The format is as follows: {@code src="SOURCE_FILE_PATH" width="PIXEL_WIDTH" height="PIXEL_HEIGHT"}
     * @param imageItem an instance of ImageItem
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitImageItem(ImageItem imageItem) {
        try {
            writeIndentation();
            String imgString = String.format("<image src=\"%s\" width=\"%s\" height=\"%s\" />\n",
                    imageItem.getSrc(), imageItem.getWidth(), imageItem.getHeight());
            writer.write(imgString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code List} with a {@code BufferedWriter} to a file.
     * This method writes the list header by iterating through the list of styles and visiting each of them with the {@code XMLStyleSerializer}.
     * Then, each item of the list is visited with this {@code XMLDomainSerializer}.
     * @param list an instance of List
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitListItem(List list) {
        try {
            writeIndentation();
            writer.write("<list");
            for (Style style : list.getStyles()){
                style.accept(xmlStyleSerializer);
            }
            writer.write(">\n");
            indent++;
            for (Iterator iter = list.createIterator(); !iter.isDone(); iter.next()) {
                iter.current().accept(this);
            }
            indent--;
            writeIndentation();
            writer.write("</list>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code Table} with a {@code BufferedWriter} to a file.
     * The table data is written as required xml attributes within an {@code <table>} tag.
     * The format is as follows: {@code rows="ROWS_NUM" cols="COLUMN_NUM"}
     * Then, each item of the table is visited with this {@code XMLDomainSerializer}.
     * Depending on the current iteration with respect to the column size, {@code <row>} and {@code </row>} tags are inserted to format the table in an appropriate way.
     * @param table an instance of Table
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitTableItem(Table table) {
        try {
            writeIndentation();
            writer.write("<table rows=\"" + table.getRows() + "\" cols=\"" + table.getCols() + "\">\n");
            indent++;
            for (Iterator iter = table.createIterator(); !iter.isDone(); iter.next()) {
                if (iter.getIndex() % table.getCols() == 0){
                    writeIndentation();
                    writer.write("<row>\n");
                    indent++;
                }
                iter.current().accept(this);
                if (iter.getIndex() % table.getCols() == table.getCols()-1){
                    indent--;
                    writeIndentation();
                    writer.write("</row>\n");
                }
            }
            indent--;
            writeIndentation();
            writer.write("</table>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
