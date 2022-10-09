package Domain.Core.Style;

import Domain.Services.Visitors.StyleVisitor;

public interface Style {

    public void accept(StyleVisitor v);
}
