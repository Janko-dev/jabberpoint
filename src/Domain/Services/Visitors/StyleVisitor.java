package Domain.Services.Visitors;

import Domain.Core.Style.BackgroundStyle;
import Domain.Core.Style.BulletPointStyle;
import Domain.Core.Style.ColorStyle;
import Domain.Core.Style.FontStyle;

public interface StyleVisitor {

    public void visitFontStyle(FontStyle fontStyle);
    public void visitColorStyle(ColorStyle colorStyle);
    public void visitBackgroundStyle(BackgroundStyle backgroundStyle);
    public void visitBulletPointStyle(BulletPointStyle bulletPointStyle);
}
