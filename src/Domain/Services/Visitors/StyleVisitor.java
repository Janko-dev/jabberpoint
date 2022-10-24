package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

/**
 * Visitor interface for the set of styling objects.
 * Guarantees that every subclass of {@code Style} has a delegated method that is visitable.
 * Used for both the rendering of the styles and for serialization of styling objects.
 */
public interface StyleVisitor {

    public void visitFontStyle(FontStyle fontStyle);
    public void visitColorStyle(ColorStyle colorStyle);
    public void visitBackgroundStyle(BackgroundStyle backgroundStyle);
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle);
}
