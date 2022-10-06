package Domain.Core;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Services.Visitor;

import java.util.ArrayList;

public class SlideShow extends SlideShowComposite{

    public String title;
    public String author;
    public String date;

    public SlideShow(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator<Slide> createIterator() {
        return new SlideShowIterator(components);
    }

    @Override
    public void accept(Visitor v) {
        // Slide show does not accept visitor
    }
}
