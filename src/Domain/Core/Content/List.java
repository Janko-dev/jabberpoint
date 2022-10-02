package Domain.Core.Content;

import Domain.Core.SlideShowComposite;

import java.util.ArrayList;
import java.util.Iterator;

public class List extends SlideShowComposite {

    public List(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
