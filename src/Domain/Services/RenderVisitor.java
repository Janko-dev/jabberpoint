package Domain.Services;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide;

import java.awt.*;

public class RenderVisitor implements Visitor {

    private Graphics graphics;
    private Rectangle boundingBox;
    private int posX = 0, posY = 0;
    private int xOffset, yOffset;

    public RenderVisitor(Graphics graphics, Rectangle boundingBox){
        this.graphics = graphics;
        xOffset = boundingBox.x;
        yOffset = boundingBox.y;
        this.boundingBox = boundingBox;
    }

    @Override
    public void visitSlide(Slide slide) {
        for (int i = 0, len = slide.getLength(); i < len; i++){
            slide.components.get(i).accept(this);
        }
    }

    @Override
    public void visitTextItem(TextItem textItem) {
        graphics.setColor(Color.BLACK); // aanpassen wanneer styling wordt toegevoegd
        int fontHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(textItem.text, xOffset + posX, yOffset + posY + fontHeight);
        posY += graphics.getFontMetrics().getHeight() + 10;
    }

    @Override
    public void visitImageItem(ImageItem imageItem) {

    }

    @Override
    public void visitListItem(List list) {
        posX += 20;
        for (int i = 0, len = list.getLength(); i < len; i++){
            list.components.get(i).accept(this);
        }
        posX -= 20;
    }

    @Override
    public void visitTableItem(Table table) {
//        int savedPosX = posX;
//        int savedPosY = posY;
        // 0,0  0,1
        // 1,0  1,1
        // 2,0  2,1

        // 0,0 = 0*3 + 0 = 0
        // 0,1 = 0*3 + 1 = 1
        // 1,0 = 1*3 + 0 = 3

        for (int i = 0; i < table.rows; i++){
            for (int j = 0; j < table.cols; j++){
                int index = (i * table.cols) + j;
                posX += j * ((boundingBox.width/table.cols) - 20);

                int tempHeight = posY;
                table.components.get(index).accept(this);

                graphics.setColor(Color.BLACK);
                graphics.drawRect(
                        posX + boundingBox.x,
                        posY - graphics.getFontMetrics().getHeight() - 10,
                        (boundingBox.width/table.cols) - 20,
                        posY - tempHeight);

                posX -= j * ((boundingBox.width/table.cols) - 20);
                posY -= graphics.getFontMetrics().getHeight() + 10;
            }
            posY += graphics.getFontMetrics().getHeight() + 10;
        }
//        posX = savedPosX;
//        posY = savedPosY;
    }
}
