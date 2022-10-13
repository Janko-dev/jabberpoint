package Domain.Core;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public abstract class Slide extends SlideShowComposite{

    public Slide(){
        super();
    }

    public String getSubject(){
        return "";
    }
}
