package Domain.Core;

import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete composite of the slide show.
 * Defines getters/setters for the properties, i.e., title, author, and date.
 * Implements the {@code accept(DomainVisitor v)} method to visit the SlideShow.
 */
public class SlideShow extends SlideShowComposite{

    private String title;
    private String author;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SlideShow(){
        super();
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitSlideShow(SlideShow)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitSlideShow(this);
    }
}
