package Domain.Services;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Iterator.Iterator;
import Domain.Core.Slide;
import Domain.Core.SlideShowComponent;

import java.io.BufferedWriter;
import java.io.IOException;

public class XMLSerializer implements Visitor {

    private int indent = 1;
    private BufferedWriter writer;

    public void setBufferedWriter(BufferedWriter writer){
        this.writer = writer;
    }

    private void writeIndentation() throws IOException {
        for (int i = 0; i < indent; i++)
            writer.write("\t");
    }

    @Override
    public void visitSlide(Slide slide) {
        try {
            writer.write("\t<slide>\n");
            indent++;
            for (Iterator<SlideShowComponent> iter = slide.createIterator(); !iter.isDone(); iter.next()) {
                iter.current().accept(this);
            }
            indent--;
            writer.write("\t</slide>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitTextItem(TextItem textItem) {
        try {
            writeIndentation();
            writer.write("<text>" + textItem.text + "</text>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitImageItem(ImageItem imageItem) {
        try {
            writeIndentation();
            writer.write("<image src=\"" + imageItem.src + "\"/>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitListItem(List list) {
        try {
            writeIndentation();
            writer.write("<list>\n");
            indent++;
            for (Iterator<SlideShowComponent> iter = list.createIterator(); !iter.isDone(); iter.next()) {
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
            writer.write("<table rows=\"" + table.rows + "\" cols=\"" + table.cols + "\">\n");
            indent++;
            for (Iterator<SlideShowComponent> iter = table.createIterator(); !iter.isDone(); iter.next()) {
                if (iter.getIndex() % table.cols == 0){
                    writeIndentation();
                    writer.write("<row>\n");
                    indent++;
                }
                iter.current().accept(this);
                if (iter.getIndex() % table.cols == table.cols-1){
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
