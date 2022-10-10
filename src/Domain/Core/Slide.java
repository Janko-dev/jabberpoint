package Domain.Core;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public class Slide extends SlideShowComposite{

    public Slide(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator createIterator() {
        return new SlideShowIterator(components);
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitSlide(this);
    }
}
