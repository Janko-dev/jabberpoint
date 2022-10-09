package Domain.Core;

import Domain.Core.Iterator.Iterable;

import java.util.ArrayList;

public abstract class SlideShowComposite extends SlideShowComponent implements Iterable {

    protected ArrayList<SlideShowComponent> components;

    public ArrayList<SlideShowComponent> getComponents() {
        return components;
    }

    public SlideShowComponent getComponent(int index){
        return this.components.get(index);
    }

    public int getLength(){
        return components.size();
    }
}
