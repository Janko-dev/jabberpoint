package Domain.Core.Slide;

import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete Slide class responsible for content in the form of slide show components that may be displayed on a slide.
 */
public class RegularSlide extends Slide{

    private String subject;

    /**
     * Overrides method from parent {@code Slide}
     * @return the subject of this regular slide.
     */
    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Constructor that calls its super constructor and sets the subject.
     * @param index Integer index used to define a default subject in the form "slide (INDEX+1)"
     */
    public RegularSlide(int index){
        super();
        subject = "Slide " + (index+1);
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitRegularSlide(RegularSlide)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitRegularSlide(this);
    }
}
