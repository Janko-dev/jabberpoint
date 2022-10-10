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

    public BackgroundStyle(int[] colorParts) {
        this.red = colorParts[0];
        this.green = colorParts[1];
        this.blue = colorParts[2];
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitBackgroundStyle(this);
    }
}