package Domain.Core.Content;

import Domain.Core.SlideItem;

import java.util.Iterator;

public class ImageItem extends SlideItem {

    private String src;

    public ImageItem(String src){
        this.src = src;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
