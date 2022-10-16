package Domain.Services.Visitors;

import Domain.Core.*;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Slide.ConcreteSlide;
import Domain.Core.Slide.TOCSlide;
import Domain.Core.Style.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DomainRenderer implements DomainVisitor {

    private static final int INDENT = 20;
    private final Graphics graphics;
    private final Rectangle bounds;
    private int posX = 0, posY = 0;
    private final int xOffset, yOffset;
    private final Font defaultFont;

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

    public DomainRenderer(Graphics graphics, int xOffset, int yOffset, Rectangle bounds, Font defaultFont){
        this.graphics = graphics;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.bounds = bounds;
        this.defaultFont = defaultFont;
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
        // predefined traversal. Hence, why the option is made available.
    }

    @Override
    public void visitConcreteSlide(ConcreteSlide slide) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        graphics.setColor(Color.BLACK);

        applyStyles(slide);
        for (int i = 0, len = slide.getLength(); i < len; i++){
            slide.getComponent(i).accept(this);
        }

        graphics.setFont(defaultFont);
        graphics.setColor(Color.BLACK);
        graphics.drawString(slide.getSubject(), yOffset, yOffset/2+defaultFont.getSize());
    }

    @Override
    public void visitTOCSlide(TOCSlide slide) {
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
        try {
            BufferedImage buffer = ImageIO.read(new File(imageItem.getSrc()));
            if (buffer != null) {
                graphics.drawImage(buffer,
                    xOffset + posX,
                    yOffset + posY,
                    imageItem.getWidth(),
                    imageItem.getHeight(),
                    null);
                posY += imageItem.getHeight();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void visitListItem(List list) {
        posX += INDENT;
        for (int i = 0, len = list.getLength(); i < len; i++){
            int savedPosX = posX;
            for (Style style : list.getStyles()){
                passDownStyle(list.getComponent(i), style);
            }
            list.getComponent(i).accept(this);
            for (Style style : list.getStyles()){
                list.getComponent(i).removeStyle(style);
            }
            posX = savedPosX;
        }
        posX -= INDENT;
    }

    private void passDownStyle(SlideShowComponent component, Style distributableStyle){
        int len = component.getStyles().size();
        if (len == 0) component.addStyle(distributableStyle);
        else {
            boolean foundIndex = false;
            for (int i = 0; i < len; i++){
                if (distributableStyle.getClass() == component.getStyle(i).getClass())
                    foundIndex = true;
            }
            if (!foundIndex) component.addStyle(distributableStyle);
        }
    }

    @Override
    public void visitTableItem(Table table) {

        for (int i = 0; i < table.getRows(); i++){
            for (int j = 0; j < table.getCols(); j++){
                int index = (i * table.getCols()) + j;
                posX += j * ((bounds.width-INDENT*2)/table.getCols()) + INDENT/2;

                int tempHeight = posY;
                table.getComponent(index).accept(this);

                int fontHeight = graphics.getFontMetrics().getHeight();
                graphics.setColor(Color.BLACK);
                graphics.drawRect(
                        posX,
                        posY + fontHeight/2,
                        ((bounds.width-INDENT*2)/table.getCols()),
                        posY - tempHeight);

                posX -= j * ((bounds.width-INDENT*2)/table.getCols()) + INDENT/2;
                posY -= fontHeight + INDENT/2;
            }
            posY += graphics.getFontMetrics().getHeight() + INDENT/2;
        }
    }
}
