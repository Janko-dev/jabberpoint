package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

/**
 * Bullet point style which encapsulates a string literal as the bullet point symbol.
 */
public class BulletPointStyle implements Style{

    private final String bulletPoint;

    public String getBulletPoint() {
        return bulletPoint;
    }

    public BulletPointStyle(String bulletPoint) {
        this.bulletPoint = bulletPoint;
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitBulletPointStyle(BulletPointStyle)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code StyleVisitor}
     */
    @Override
    public void accept(StyleVisitor v) {
        v.visitBulletPointStyle(this);
    }
}
