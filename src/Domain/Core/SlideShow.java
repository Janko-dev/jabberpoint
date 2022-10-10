package Domain.Core;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

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
        this.components = new ArrayList<>();
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitSlideShow(this);
    }
}
