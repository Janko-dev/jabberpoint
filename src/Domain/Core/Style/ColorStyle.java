package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;
import Utils.ErrorUtils;

public class ColorStyle implements Style{
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

    public ColorStyle(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public ColorStyle(int[] colorParts) {
        ErrorUtils.assertEquals(colorParts.length == 3,
                "Array of colors must have a length of exactly 3");
        this.red = colorParts[0];
        this.green = colorParts[1];
        this.blue = colorParts[2];
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitColorStyle(this);
    }
}
