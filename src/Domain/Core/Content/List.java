package Domain.Core.Content;

import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete composite that holds a list of slide show components.
 */
public class List extends SlideShowComposite {

    public List(){
        super();
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitListItem(List)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitListItem(this);
    }
}
