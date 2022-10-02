package Domain.Core;

import java.util.ArrayList;
import java.util.Iterator;

public class SlideShow extends SlideShowComposite{

    public String title;
    public String author;
    public String date;

    public SlideShow(){
        this.components = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
