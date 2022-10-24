package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

/**
 * Color style that maintains 3 color components as integers ranging from 0-255
 */
public class ColorStyle extends AbstractColorStyle implements Style{

    public ColorStyle(int red, int green, int blue) {
        super(red, green, blue);
    }

    public ColorStyle(int[] colorParts) {
        super(colorParts);
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitColorStyle(ColorStyle)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code StyleVisitor}
     */
    @Override
    public void accept(StyleVisitor v) {
        v.visitColorStyle(this);
    }
}
