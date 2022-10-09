package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

public class FontStyle implements Style{
    private String fontName;
    private int fontSize;

    public String getFontName() {
        return fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public FontStyle(String fontName, int fontSize){
        this.fontName = fontName;
        this.fontSize = fontSize;
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitFontStyle(this);
    }

}
