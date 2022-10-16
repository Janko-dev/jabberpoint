package Domain.Core.Slide;

import Domain.Services.Visitors.DomainVisitor;

public class TOCSlide extends Slide {

    @Override
    public void accept(DomainVisitor v) {
        v.visitTOCSlide(this);
    }
}