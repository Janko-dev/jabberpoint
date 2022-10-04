package Domain.Core;

import java.util.ArrayList;

public class Slide extends SlideShowComposite{

    public Slide(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator<SlideItem> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitSlide(this);
    }
}
