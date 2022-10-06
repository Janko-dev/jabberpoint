package Domain.Core.Content;

import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideItem;
import Domain.Services.Visitor;

public class ImageItem extends SlideItem {

    public String src;

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
