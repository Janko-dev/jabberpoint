package Domain.Core.Slide;

import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete Slide class responsible for the Table of Contents.
 * Does not define state, but is visitable which delegates custom behavior depending on the domain visitor
 */
public class TOCSlide extends Slide {

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitTOCSlide(TOCSlide)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitTOCSlide(this);
    }
}
