package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;
import Utils.ErrorUtils;

/**
 * Background color style that maintains 3 color components as integers ranging from 0-255
 */
public class BackgroundStyle extends AbstractColorStyle implements Style{

    public BackgroundStyle(int red, int green, int blue) {
        super(red, green, blue);
    }

    public BackgroundStyle(int[] colorParts) {
        super(colorParts);
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitBackgroundStyle(BackgroundStyle)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code StyleVisitor}
     */
    @Override
    public void accept(StyleVisitor v) {
        v.visitBackgroundStyle(this);
    }
}
