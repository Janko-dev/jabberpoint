package Domain.Core.Content;

import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

public class List extends SlideShowComposite {

    public List(){
        super();
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitListItem(this);
    }
}
