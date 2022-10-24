package Domain.Core.Style;

import Utils.ErrorUtils;

public class AbstractColorStyle {

    protected final int red, green, blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    /**
     * Constructor with RGB as separate parameters
     * @param red int red channel (0-255)
     * @param green int green channel (0-255)
     * @param blue int blue channel (0-255)
     */
    public AbstractColorStyle(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Constructor with RGB as int array parameter.
     * Asserts length of the array is equal to 3
     * @param colorParts Integer array of length 3, where
     *                   index 0 = red channel
     *                   index 1 = green channel
     *                   index 2 = blue channel
     * @exception RuntimeException thrown when array length assertion fails.
     */
    public AbstractColorStyle(int[] colorParts) {
        ErrorUtils.assertEquals(colorParts.length == 3,
                "Array of colors must have a length of exactly 3");
        this.red = colorParts[0];
        this.green = colorParts[1];
        this.blue = colorParts[2];
    }
}
