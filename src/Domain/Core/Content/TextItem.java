package Domain.Core.Content;

import Domain.Core.Iterator;
import Domain.Core.SlideItem;
import Domain.Core.Visitor;

public class TextItem extends SlideItem {

    public String text;

    public TextItem(String text) {
        this.text = text;
    }
    @Override
    public Iterator<SlideItem> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitTextItem(this);
    }
}
