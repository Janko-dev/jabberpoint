package Domain.Core.Content;

import Domain.Core.Iterator;
import Domain.Core.SlideItem;
import Domain.Core.Visitor;

public class ImageItem extends SlideItem {

    private String src;

    public ImageItem(String src){
        this.src = src;
    }

    @Override
    public Iterator<SlideItem> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitImageItem(this);
    }
}
