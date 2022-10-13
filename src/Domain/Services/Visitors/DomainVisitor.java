package Domain.Services.Visitors;

import Domain.Core.ConcreteSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.SlideShow;
import Domain.Core.TOCSlide;

public interface DomainVisitor {

    public void visitSlideShow(SlideShow slideShow);
    public void visitConcreteSlide(ConcreteSlide slide);
    public void visitTOCSlide(TOCSlide slide);
    public void visitTextItem(TextItem textItem);
    public void visitImageItem(ImageItem imageItem);
    public void visitListItem(List list);
    public void visitTableItem(Table table);

}
