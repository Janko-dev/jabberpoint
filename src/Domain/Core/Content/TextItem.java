package Domain.Core.Content;

import Domain.Core.SlideItem;

import java.util.Iterator;

public class TextItem extends SlideItem {

    public String text;

    public TextItem(String text) {
        this.text = text;
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}
