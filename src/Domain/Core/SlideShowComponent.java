package Domain.Core;

import Domain.Core.Style.Style;

import java.awt.*;
import java.util.ArrayList;

public abstract class SlideShowComponent implements Iterable {

    public Point position;
    public int depth;
    public ArrayList<Style> styles;
}
