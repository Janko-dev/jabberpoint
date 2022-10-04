package Domain.Core;

import java.util.ArrayList;

public class SlideShowIterator implements Iterator<Slide>{

    private ArrayList<SlideShowComponent> slides;
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
        return index >= slides.size()-1;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Slide current() {
        return (Slide)slides.get(index);
    }

    @Override
    public void setCurrent(int index) {
        this.index = index;
    }
}
