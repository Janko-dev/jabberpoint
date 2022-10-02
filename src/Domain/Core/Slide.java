package Domain.Core;

import java.util.ArrayList;
import java.util.Iterator;

public class Slide extends SlideShowComposite{

    public Slide(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
