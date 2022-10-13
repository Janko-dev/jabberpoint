package Domain.Core.Content;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public class List extends SlideShowComposite {

    public List(){
        super();
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitListItem(this);
    }
}
