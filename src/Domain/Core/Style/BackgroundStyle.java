package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

public class BackgroundStyle implements Style{

    private int red, green, blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public BackgroundStyle(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitBackgroundStyle(this);
    }
}
