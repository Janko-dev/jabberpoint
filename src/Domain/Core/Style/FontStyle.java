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

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public FontStyle(){
        this.fontName = "Arial";
        this.fontSize = 20;
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitFontStyle(this);
    }

}
