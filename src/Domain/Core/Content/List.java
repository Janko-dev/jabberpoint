package Domain.Core.Content;

import Domain.Core.Iterator;
import Domain.Core.SlideShowComponent;
import Domain.Core.SlideShowComposite;
import Domain.Core.Visitor;

import java.util.ArrayList;

public class List extends SlideShowComposite {

    public List(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator<SlideShowComponent> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitListItem(this);
    }
}
