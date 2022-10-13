package Domain.Core.Iterator;

import Domain.Core.SlideShowComponent;

import java.util.ArrayList;

public class SlideShowIterator implements Iterator{

    private final ArrayList<SlideShowComponent> slides;
    private int index;

    public SlideShowIterator(ArrayList<SlideShowComponent> slides){
        this.slides = slides;
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
        return index >= slides.size();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public SlideShowComponent current() {
        return slides.get(index);
    }

}
