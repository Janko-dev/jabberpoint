package Domain.Core;

import Domain.Core.Iterator.Iterable;
import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;

import java.util.ArrayList;

public abstract class SlideShowComposite extends SlideShowComponent implements Iterable {

    protected ArrayList<SlideShowComponent> components;

    public ArrayList<SlideShowComponent> getComponents() {
        return components;
    }

    public SlideShowComponent getComponent(int index){
        return this.components.get(index);
    }

    public SlideShowComposite(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator createIterator() {
        return new SlideShowIterator(this.components);
    }

    public int getLength(){
        return components.size();
    }
}
