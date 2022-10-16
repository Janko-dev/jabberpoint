package Domain.Services.Visitors;

import Domain.Core.ConcreteSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideShow;
import Domain.Core.Style.Style;
import Domain.Core.TOCSlide;

import java.io.BufferedWriter;
import java.io.IOException;

public class XMLDomainSerializer implements DomainVisitor {

    private int indent = 1;
    private BufferedWriter writer;
    private XMLStyleSerializer xmlStyleSerializer = new XMLStyleSerializer();

    public void setBufferedWriter(BufferedWriter writer){
        this.writer = writer;
        xmlStyleSerializer.setBufferedWriter(writer);
    }

    private void writeIndentation() throws IOException {
        for (int i = 0; i < indent; i++)
            writer.write("\t");
    }

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

    @Override
    public void visitConcreteSlide(ConcreteSlide slide) {
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

    @Override
    public void visitTOCSlide(TOCSlide slide) {
        try {
            writer.write("\t<slide type=\"toc\"/>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
