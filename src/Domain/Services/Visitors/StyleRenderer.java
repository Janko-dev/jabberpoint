package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

import javax.swing.*;
import java.awt.*;

public class StyleRenderer implements StyleVisitor{

    private final DomainRenderer renderer;

    public StyleRenderer(DomainRenderer renderer){
        this.renderer = renderer;
    }

    @Override
    public void visitFontStyle(FontStyle fontStyle) {
        Font font = new Font(fontStyle.getFontName(), Font.PLAIN, fontStyle.getFontSize());
        renderer.getGraphics().setFont(font);
    }

    @Override
    public void visitColorStyle(ColorStyle colorStyle) {
        Color color = new Color(colorStyle.getRed(), colorStyle.getGreen(), colorStyle.getBlue());
        renderer.getGraphics().setColor(color);
    }

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

    @Override
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle) {
        int fontHeight = renderer.getGraphics().getFontMetrics().getHeight();
        renderer.getGraphics().drawString(bulletPointStyle.getBulletPoint(),
                renderer.getxOffset() + renderer.getPosX(),
                renderer.getyOffset() + renderer.getPosY() + fontHeight);
        renderer.setPosX(renderer.getPosX() + renderer.getGraphics().getFont().getSize());
    }
}
