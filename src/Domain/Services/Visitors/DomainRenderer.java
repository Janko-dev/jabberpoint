package Domain.Services.Visitors;

import Domain.Core.*;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Core.Slide.RegularSlide;
import Domain.Core.Slide.TOCSlide;
import Domain.Core.Style.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Concrete renderer class that is responsible for appropriately rendering core domain components.
 * This class implements the {@code DomainVisitor}, which means that a method for every kind of concrete domain component is implemented.
 */
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

    /**
     * Constructor of the domain renderer that is dependent on {@code java.awt.Graphics} object.
     * @param graphics The graphics context of type {@code java.awt.Graphics}
     * @param xOffset offset on the x-axis where to draw.
     * @param yOffset offset on the y-axis where to draw.
     * @param bounds The bounds of the window of type {@code java.awt.Rectangle}
     * @param defaultFont A default font of type {@code java.awt.Font}, used to draw meta information in a common style.
     */
    public DomainRenderer(Graphics graphics, int xOffset, int yOffset, Rectangle bounds, Font defaultFont){
        this.graphics = graphics;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.bounds = bounds;
        this.defaultFont = defaultFont;
    }

    /**
     * Applies the styles of a provided {@code SlideShowComponent} by instantiating a new {@code StyleRenderer},
     * iterating through the list of styles, and accepting each style with the style renderer.
     * @param component An instance of {@code SlideShowComponent} whose styles should be applied.
     */
    private void applyStyles(SlideShowComponent component){
        StyleRenderer styleRenderer = new StyleRenderer(this);
        for (Style style : component.getStyles()){
            style.accept(styleRenderer);
        }
    }

    /**
     * Currently, there is no need to visit the entire slide show and render it at once.
     * The option is provided, for future considerations.
     * @param slideShow An instance of {@code SlideShow}
     */
    @Override
    public void visitSlideShow(SlideShow slideShow) {
        // Cannot render entire slideshow at once.
        // Possible consideration when the slideshow should navigate automatically based on
        // predefined traversal. Hence, why the option is made available.
    }

    /**
     * Visits and renders a regular slide.
     * First, a default background is rendered, then the encapsulated styles of the {@code RegularSlide} object are applied.
     * After that, iteration through the slide ensures that each slide item is visited and rendered.
     * Lastly, the subject of the slide is rendered in the section of the window where the slideshow meta information is displayed.
     * @param slide An instance of {@code RegularSlide}
     */
    @Override
    public void visitRegularSlide(RegularSlide slide) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, bounds.width, bounds.height);

        applyStyles(slide);
        for (Iterator iter = slide.createIterator(); !iter.isDone(); iter.next()){
            iter.current().accept(this);
        }

        graphics.setFont(defaultFont);
        graphics.setColor(Color.BLACK);
        graphics.drawString(slide.getSubject(), yOffset, yOffset/2+defaultFont.getSize());
    }

    /**
     * Visits and renders a Table of Contents (TOC) slide.
     * First, a default background is rendered, then the encapsulated styles of the {@code TOCSlide} object are applied.
     * After that, iteration through the slide ensures that each slide item is visited and rendered.
     * In the case of a TOC slide, the structure of its components is well known at compile-time.
     * This is always a {@code TextItem} component as the header of the TOC, and a {@code List} of {@code TextItem} components corresponding with the subjects of the slides.
     * @param slide An instance of {@code TOCSlide}
     */
    @Override
    public void visitTOCSlide(TOCSlide slide) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        applyStyles(slide);
        for (Iterator iter = slide.createIterator(); !iter.isDone(); iter.next()){
            iter.current().accept(this);
        }
    }

    /**
     * Visits and renders a text item.
     * To draw a piece of text, its styling must first be applied.
     * Thereafter, font meta information must be determined to set the cursor position appropriately, after drawing of the text content.
     * @param textItem An instance of {@code TextItem}
     */
    @Override
    public void visitTextItem(TextItem textItem) {
        graphics.setColor(Color.BLACK);
        applyStyles(textItem);
        int fontHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(textItem.getText(), xOffset + posX, yOffset + posY + fontHeight);
        posY += graphics.getFontMetrics().getHeight() + 10;
    }

    /**
     * Visits and renders an image item.
     * To draw an image to the window, its styling must first be applied.
     * Thereafter, an attempt is made to read the image from the file system.
     * If this succeeds, then the image is drawn to the window, at the current cursor position and with the image width and height.
     * In the case the file could not be read into an ImageInputStream, nothing is drawn to the window.
     * This strategy adheres to a lazy evaluation. Images are read at runtime instead of at compile-time.
     * This way the domain is not cluttered with objects like {@code java.awt.BufferedImage}.
     * @param imageItem An instance of {@code ImageItem}
     *  IOException thrown when any IO errors occur.
     */
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

    /**
     * Visits and renders a list.
     * A standard amount of indentation is applied to the cursor position before the list is rendered.
     * The cursor position is recovered after the list is rendered.
     * Each item in the list is iterated over, and visited and rendered.
     * However, before visitation, the list styles are passed down to the current list component.
     * After visitation, the same list styles are removed from the current list component.
     * This mechanism allows for varying nested list styles, such as the {@code BulletPointStyle} on various depth levels.
     * @param list An instance of {@code List}
     */
    @Override
    public void visitListItem(List list) {
        posX += INDENT;
        for (Iterator iter = list.createIterator(); !iter.isDone(); iter.next()){
            int savedPosX = posX;
            // pass down styles
            for (Style style : list.getStyles()){
                passDownStyle(iter.current(), style);
            }
            iter.current().accept(this);

            // remove passed down styles
            for (Style style : list.getStyles()){
                iter.current().removeStyle(style);
            }
            posX = savedPosX;
        }
        posX -= INDENT;
    }

    /**
     * Passes down a distributable style to a supplied slide show component, if and only if the type of the style is not contained in the component list of styles.
     * @param component An instance of {@code SlideShowComponent} potentially gets a distributable style.
     * @param distributableStyle An instance of {@code Style} that is attempted to pass down to the component.
     */
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

    /**
     * Visits and renders a Table.
     * The table is very limited in that it disregards varying amounts of sizes in its elements.
     * Currently, the best use-case for the table is as a table of text items that all have the same font size.
     * Elements in the table are saved in a one-dimensional array list, which means conversion to two-dimensionality is used to render the elements appropriately.
     * @param table An instance of {@code Table}
     */
    @Override
    public void visitTableItem(Table table) {

        for (int i = 0; i < table.getRows(); i++){
            for (int j = 0; j < table.getCols(); j++){
                // calculate 1d index from rows and cols
                int index = (i * table.getCols()) + j;
                posX += j * ((bounds.width-INDENT*2)/table.getCols()) + INDENT/2;

                int tempHeight = posY;
                table.getComponent(index).accept(this);

                // draw rectangular border
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
