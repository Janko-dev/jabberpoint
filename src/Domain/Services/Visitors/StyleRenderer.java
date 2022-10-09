package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

import java.awt.*;

public class StyleRenderer implements StyleVisitor{

    private Graphics graphics;

    public StyleRenderer(Graphics graphics){
        this.graphics = graphics;
    }

    @Override
    public void visitFontStyle(FontStyle fontStyle) {
        Font font = new Font(fontStyle.getFontName(), Font.PLAIN, fontStyle.getFontSize());
        graphics.setFont(font);
    }

    @Override
    public void visitColorStyle(ColorStyle colorStyle) {
        Color color = new Color(colorStyle.getRed(), colorStyle.getGreen(), colorStyle.getBlue());
        graphics.setColor(color);
    }

    @Override
    public void visitBackgroundStyle(BackgroundStyle backgroundStyle) {
        Color color = new Color(
                backgroundStyle.getRed(),
                backgroundStyle.getGreen(),
                backgroundStyle.getBlue());
        graphics.setColor(color);
    }

    @Override
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle) {
//        graphics.drawString(bulletPointStyle.getBulletPoint(), );
    }
}
