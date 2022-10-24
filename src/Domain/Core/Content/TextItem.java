package Domain.Core.Content;

import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete leaf of the slide show components that represents a textual item as a String.
 */
public class TextItem extends SlideItem {

    private String text;

    public String getText() {
        return text;
    }

    public TextItem(String text) {
        this.text = text;
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitTextItem(TextItem)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitTextItem(this);
    }
}
