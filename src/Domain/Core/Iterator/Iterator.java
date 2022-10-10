package Domain.Core.Iterator;

import Domain.Core.SlideShowComponent;

public interface Iterator {

    public void first();
    public void next();
    public void previous();
    public boolean isDone();
    public int getIndex();
    public SlideShowComponent current();
}
