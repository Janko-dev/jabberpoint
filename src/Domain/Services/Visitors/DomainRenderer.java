package Domain.Services.Visitors;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide;
import Domain.Core.SlideShow;
import Domain.Core.SlideShowComponent;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.Style;

import java.awt.*;

public class DomainRenderer implements DomainVisitor {

    private final Graphics graphics;
    private final Rectangle bounds;
    private int posX = 0, posY = 0;
    private final int xOffset, yOffset;

    public Graphics getGraphics() {
        return graphics;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public DomainRenderer(Graphics graphics, int xOffset, int yOffset, Rectangle bounds){
        this.graphics = graphics;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.bounds = bounds;
    }

    private void applyStyles(SlideShowComponent component){
        StyleRenderer styleRenderer = new StyleRenderer(this);
        for (Style style : component.getStyles()){
            style.accept(styleRenderer);
        }
    }

    @Override
    public void visitSlideShow(SlideShow slideShow) {
        // Cannot render entire slideshow at once.
        // Possible consideration when the slideshow should navigate automatically based on
        // predefined traversal.
    }

    @Override
    public void visitSlide(Slide slide) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        applyStyles(slide);
        for (int i = 0, len = slide.getLength(); i < len; i++){
            slide.getComponent(i).accept(this);
        }
    }

    @Override
    public void visitTextItem(TextItem textItem) {
        graphics.setColor(Color.BLACK);
        applyStyles(textItem);
        int fontHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(textItem.getText(), xOffset + posX, yOffset + posY + fontHeight);
        posY += graphics.getFontMetrics().getHeight() + 10;
    }

    @Override
    public void visitImageItem(ImageItem imageItem) {
        applyStyles(imageItem);
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
            int savedPosX = posX;
            list.getComponent(i).accept(this);
            posX = savedPosX;
        }
        posX -= 20;
    }

    @Override
    public void visitTableItem(Table table) {

        for (int i = 0; i < table.getRows(); i++){
            for (int j = 0; j < table.getCols(); j++){
                int index = (i * table.getCols()) + j;
                posX += j * ((bounds.width/table.getCols()) - 20);

                int tempHeight = posY;
                table.getComponent(index).accept(this);

                int fontHeight = graphics.getFontMetrics().getHeight();
                graphics.setColor(Color.BLACK);
                graphics.drawRect(
                        posX,
                        posY + fontHeight/2,
                        (bounds.width/table.getCols()) - 20,
                        posY - tempHeight);

                posX -= j * ((bounds.width/table.getCols()) - 20);
                posY -= fontHeight + 10;
            }
            posY += graphics.getFontMetrics().getHeight() + 10;
        }
    }
}
