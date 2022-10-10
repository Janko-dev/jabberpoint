package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

import java.io.BufferedWriter;
import java.io.IOException;

public class XMLStyleSerializer implements StyleVisitor{

    private BufferedWriter writer;

    public void setBufferedWriter(BufferedWriter writer){
        this.writer = writer;
    }

    @Override
    public void visitFontStyle(FontStyle fontStyle) {
        try {
            writer.write(" font=\"" + fontStyle.getFontName() + "\"");
            writer.write(" size=\"" + fontStyle.getFontSize() + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitColorStyle(ColorStyle colorStyle) {
        try {
            String colorAttr = String.format(" color=\"%s, %s, %s\"",
                    colorStyle.getRed(),
                    colorStyle.getGreen(),
                    colorStyle.getBlue());
            writer.write(colorAttr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitBackgroundStyle(BackgroundStyle backgroundStyle) {
        try {
            String colorAttr = String.format(" background=\"%s, %s, %s\"",
                    backgroundStyle.getRed(),
                    backgroundStyle.getGreen(),
                    backgroundStyle.getBlue());
            writer.write(colorAttr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle) {
        try {
            String bulletPointAttr = String.format(" bullet_point=\"%s\"", bulletPointStyle.getBulletPoint());
            writer.write(bulletPointAttr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
