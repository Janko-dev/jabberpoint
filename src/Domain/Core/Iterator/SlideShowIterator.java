package Domain.Core.Iterator;

import Domain.Core.SlideShowComponent;

import java.util.ArrayList;

/**
 * Concrete implementation of the {@code Iterator}.
 * Maintains the current step in the iteration as an int index that gets manipulated in the methods.
 */
public class SlideShowIterator implements Iterator{

    private final ArrayList<SlideShowComponent> components;
    private int index;

    public SlideShowIterator(ArrayList<SlideShowComponent> components){
        this.components = components;
        first();
    }

    /**
     * Sets index to 0, making the iterator reset to the first element.
     */
    @Override
    public void first() {
        index = 0;
    }

    /**
     * Advances the index one step forward.
     */
    @Override
    public void next() {
        index++;
    }

    /**
     * Advances the index one step backwards.
     */
    @Override
    public void previous() {
        index--;
    }

    /**
     * @return a boolean representing whether the index lies outside the range of components.
     */
    @Override
    public boolean isDone() {
        return index >= components.size();
    }

    /**
     * @return the current index as an Integer.
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * @return the current {@code SlideShowComponent} based on the index.
     */
    @Override
    public SlideShowComponent current() {
        return components.get(index);
    }

}
