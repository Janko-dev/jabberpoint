package Domain.Core;

import Domain.Core.Iterator.Iterable;
import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;

import java.util.ArrayList;

/**
 * Abstract base class of all domain composite components.
 * This class defines a list of components and basic operations.
 * Furthermore, the {@code Iterable} interface is implemented.
 */
public abstract class SlideShowComposite extends SlideShowComponent implements Iterable {

    protected ArrayList<SlideShowComponent> components;

    public ArrayList<SlideShowComponent> getComponents() {
        return components;
    }

    public void addComponent(SlideShowComponent component) {
        components.add(component);
    }

    public SlideShowComponent getComponent(int index){
        return this.components.get(index);
    }

    public SlideShowComposite(){
        this.components = new ArrayList<>();
    }

    /**
     * @return an Iterator of type SlideShowIterator, which is injected with the components list.
     */
    @Override
    public Iterator createIterator() {
        return new SlideShowIterator(this.components);
    }

    /**
     * @return Amount of components in list
     */
    public int getLength(){
        return components.size();
    }
}
