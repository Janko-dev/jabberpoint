package Domain.Core;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideIterator;
import Domain.Services.Visitor;

import java.util.ArrayList;

public class Slide extends SlideShowComposite{

    public Slide(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator<SlideShowComponent> createIterator() {
        return new SlideIterator(components);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSlide(this);
    }
}
