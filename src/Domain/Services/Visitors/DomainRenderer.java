package Domain.Services.Visitors;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide;
import Domain.Core.SlideShow;

import java.awt.*;

public class DomainRenderer implements DomainVisitor {

    private Graphics graphics;
    private Rectangle boundingBox;
    private int posX = 0, posY = 0;
    private int xOffset, yOffset;

    public DomainRenderer(Graphics graphics, Rectangle boundingBox){
        this.graphics = graphics;
        xOffset = boundingBox.x;
        yOffset = boundingBox.y;
        this.boundingBox = boundingBox;
    }

    @Override
    public void visitSlideShow(SlideShow slideShow) {
        // Cannot render entire slideshow at once.
        // Possible consideration when the slideshow should navigate automatically based on
        // predefined traversal.
    }

    @Override
    public void visitSlide(Slide slide) {
        for (int i = 0, len = slide.getLength(); i < len; i++){
            slide.getComponent(i).accept(this);
        }
    }

    @Override
    public void visitTextItem(TextItem textItem) {
        graphics.setColor(Color.BLACK); // aanpassen wanneer styling wordt toegevoegd
        int fontHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(textItem.getText(), xOffset + posX, yOffset + posY + fontHeight);
        posY += graphics.getFontMetrics().getHeight() + 10;
    }

    @Override
    public void visitImageItem(ImageItem imageItem) {
        graphics.drawImage(imageItem.getBuffer(),
                xOffset + posX,
                yOffset + posY,
                imageItem.getWidth(),
                imageItem.getHeight(),
                null);
        posY += imageItem.getHeight();
    }

    @Override
    public void visitListItem(List list) {
        posX += 20;
        for (int i = 0, len = list.getLength(); i < len; i++){
            list.getComponent(i).accept(this);
        }
        posX -= 20;
    }

    @Override
    public void visitTableItem(Table table) {

        for (int i = 0; i < table.getRows(); i++){
            for (int j = 0; j < table.getCols(); j++){
                int index = (i * table.getCols()) + j;
                posX += j * ((boundingBox.width/table.getCols()) - 20);

                int tempHeight = posY;
                table.getComponent(index).accept(this);

                graphics.setColor(Color.BLACK);
                graphics.drawRect(
                        posX + boundingBox.x,
                        posY - graphics.getFontMetrics().getHeight() - 10,
                        (boundingBox.width/table.getCols()) - 20,
                        posY - tempHeight);

                posX -= j * ((boundingBox.width/table.getCols()) - 20);
                posY -= graphics.getFontMetrics().getHeight() + 10;
            }
            posY += graphics.getFontMetrics().getHeight() + 10;
        }
    }
}
