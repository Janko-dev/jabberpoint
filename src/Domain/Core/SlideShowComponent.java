package Domain.Core;

import Domain.Core.Style.Style;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public abstract class SlideShowComponent {

    public ArrayList<Style> styles;

    public abstract void accept(DomainVisitor v);
}
