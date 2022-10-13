package Domain.Core;

import Domain.Services.Visitors.DomainVisitor;

public class ConcreteSlide extends Slide{

    private String subject;

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ConcreteSlide(){
        super();
        subject = "";
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitConcreteSlide(this);
    }
}
