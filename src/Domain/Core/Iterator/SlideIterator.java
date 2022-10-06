package Domain.Core.Iterator;

import Domain.Core.SlideShowComponent;

import java.util.ArrayList;

public class SlideIterator implements Iterator<SlideShowComponent>{

    private ArrayList<SlideShowComponent> items;
    private int index;

    public SlideIterator(ArrayList<SlideShowComponent> items){
        this.items = items;
        first();
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        index++;
    }

    @Override
    public void previous() {
        index--;
    }

    @Override
    public boolean isDone() {
        return index >= items.size()-1;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public SlideShowComponent current() {
        return items.get(index);
    }

    @Override
    public void setCurrent(int index) {
        this.index = index;
    }
}
