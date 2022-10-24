package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

/**
 * Style interface which ensures that every style is referable by {@code Style}, and is visitable by a {@code StyleVisitor}
 */
public interface Style {

    public void accept(StyleVisitor v);
}
