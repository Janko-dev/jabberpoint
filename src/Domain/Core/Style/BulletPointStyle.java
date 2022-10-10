package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

public class BulletPointStyle implements Style{

    private String bulletPoint;

    public String getBulletPoint() {
        return bulletPoint;
    }

    public BulletPointStyle(String bulletPoint) {
        this.bulletPoint = bulletPoint;
    }

    @Override
    public void accept(StyleVisitor v) {
        v.visitBulletPointStyle(this);
    }
}
