package Domain.Services.Visitors;

import Domain.Core.Slide.RegularSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.SlideShow;
import Domain.Core.Slide.TOCSlide;

/**
 * Visitor interface for the set of domain concrete classes.
 * Guarantees that every leaf of the {@code SlideShowComponent} hierarchy has a delegated method that is visitable.
 * Used for both the rendering of the domain and for serialization of a slide show.
 */
public interface DomainVisitor {

    public void visitSlideShow(SlideShow slideShow);
    public void visitConcreteSlide(RegularSlide slide);
    public void visitTOCSlide(TOCSlide slide);
    public void visitTextItem(TextItem textItem);
    public void visitImageItem(ImageItem imageItem);
    public void visitListItem(List list);
    public void visitTableItem(Table table);

}
