package Domain.Core.Content;

import Domain.Services.Visitors.DomainVisitor;

public class TextItem extends SlideItem {

    private String text;

    public String getText() {
        return text;
    }

    public TextItem(String text) {
        this.text = text;
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitTextItem(this);
    }
}
