package Domain.Core;

import Domain.Core.Iterator.Iterable;
import Domain.Core.Style.Style;
import Domain.Services.Visitor;

import java.util.ArrayList;

public abstract class SlideShowComponent implements Iterable {

    public int depth;
    public ArrayList<Style> styles;

    public abstract void accept(Visitor v);
}
