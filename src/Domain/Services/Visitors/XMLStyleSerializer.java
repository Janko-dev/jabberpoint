package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Concrete xml serialization class that is responsible for appropriately writing styling data to a file.
 * This class implements the {@code StyleVisitor}, which means that a method for every kind of style is implemented.
 */
public class XMLStyleSerializer implements StyleVisitor{

    private BufferedWriter writer;

    /**
     * Set the current {@code BufferedWriter} object to a given {@code BufferedWriter}.
     * The contractual methods rely on using a {@code BufferedWriter} object, so this method needs to be called before any of the visit methods is called.
     * @param writer an instance of the {@code BufferedWriter} class
     */
    public void setBufferedWriter(BufferedWriter writer){
        this.writer = writer;
    }

    /**
     * Writes the {@code FontStyle} with a {@code BufferedWriter} to a file.
     * format of the string that is written is: {@code font="FONT_NAME" size="FONT_SIZE"}
     * @param fontStyle an instance of FontStyle
     * @exception IOException thrown when any IO errors occur.
     */
    @Override
    public void visitFontStyle(FontStyle fontStyle) {
        try {
            writer.write(" font=\"" + fontStyle.getFontName() + "\"");
            writer.write(" size=\"" + fontStyle.getFontSize() + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the {@code ColorStyle} with a {@code BufferedWriter} to a file.
     * format of the string that is written is: {@code color="RED, GREEN, BLUE"}
     * @param colorStyle an instance of ColorStyle
     * @exception IOException thrown when any IO errors occur.
     */
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

    /**
     * Writes the {@code BackgroundStyle} with a {@code BufferedWriter} to a file.
     * format of the string that is written is: {@code background="RED, GREEN, BLUE"}
     * @param backgroundStyle an instance of BackgroundStyle
     * @exception IOException thrown when any IO errors occur.
     */
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

    /**
     * Writes the {@code BulletPointStyle} with a {@code BufferedWriter} to a file.
     * format of the string that is written is: {@code bullet_point="SYMBOL"}
     * @param bulletPointStyle an instance of BulletPointStyle
     * @exception IOException thrown when any IO errors occur.
     */
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
