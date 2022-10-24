package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

/**
 * Font style which controls both:
 * - the font family
 * - and the font size
 */
public class FontStyle implements Style{
    private String fontName;
    private int fontSize;

    public String getFontName() {
        return fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Default constructor without parameters
     */
    public FontStyle(){
        this.fontName = "Arial";
        this.fontSize = 20;
    }

    /**
     * Constructor with parameters to inject.
     * @param fontName One of valid font names as String.
     * @param fontSize The size of the font as Integer.
     */
    public FontStyle(String fontName, int fontSize){
        this.fontName = fontName;
        this.fontSize = fontSize;
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitFontStyle(FontStyle)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code StyleVisitor}
     */
    @Override
    public void accept(StyleVisitor v) {
        v.visitFontStyle(this);
    }

}
