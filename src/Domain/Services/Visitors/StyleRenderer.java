package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

import java.awt.*;

/**
 * Concrete renderer class that is responsible for appropriately rendering styling attributes of domain components.
 * This class implements the {@code StyleVisitor}, which means that a method for every kind of style is implemented.
 */
public class StyleRenderer implements StyleVisitor{

    private final DomainRenderer renderer;

    /**
     * The constructor of the {@code StyleRenderer} takes an instance of {@code DomainRenderer}, because styling should be related to a domain renderer context.
     * @param renderer an instance of {@code DomainRenderer}
     */
    public StyleRenderer(DomainRenderer renderer){
        this.renderer = renderer;
    }

    /**
     * Instantiates a new {@code Font} by using the properties of the {@code FontStyle}, and setting the flag to {@code Font.PLAIN}.
     * Thereafter, the current font in the domain renderer is set to the instantiated font.
     * @param fontStyle an instance of FontStyle
     */
    @Override
    public void visitFontStyle(FontStyle fontStyle) {
        Font font = new Font(fontStyle.getFontName(), Font.PLAIN, fontStyle.getFontSize());
        renderer.getGraphics().setFont(font);
    }

    /**
     * Instantiates a new {@code Color} by using the properties of the {@code ColorStyle}.
     * Thereafter, the current color in the domain renderer is set to the instantiated color.
     * @param colorStyle an instance of ColorStyle
     */
    @Override
    public void visitColorStyle(ColorStyle colorStyle) {
        Color color = new Color(colorStyle.getRed(), colorStyle.getGreen(), colorStyle.getBlue());
        renderer.getGraphics().setColor(color);
    }

    /**
     * Instantiates a new {@code Color} by using the properties of the {@code BackgroundStyle}.
     * Thereafter, the current color in the domain renderer is set to the instantiated color, and rectangle covering the entire window is rendered.
     * @param backgroundStyle an instance of BackgroundStyle
     */
    @Override
    public void visitBackgroundStyle(BackgroundStyle backgroundStyle) {
        Color color = new Color(
                backgroundStyle.getRed(),
                backgroundStyle.getGreen(),
                backgroundStyle.getBlue());
        renderer.getGraphics().setColor(color);
        renderer.getGraphics().fillRect(0, 0,
                renderer.getBounds().width,
                renderer.getBounds().height);
    }

    /**
     * Draws the symbolic representation of a bullet point as a string of characters to the screen.
     * @param bulletPointStyle an instance of BulletPointStyle
     */
    @Override
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle) {
        int fontHeight = renderer.getGraphics().getFontMetrics().getHeight();
        renderer.getGraphics().drawString(bulletPointStyle.getBulletPoint(),
                renderer.getxOffset() + renderer.getPosX(),
                renderer.getyOffset() + renderer.getPosY() + fontHeight);
        renderer.setPosX(renderer.getPosX() + renderer.getGraphics().getFont().getSize());
    }
}
