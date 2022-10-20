package Domain.Services.Visitors;

import Domain.Core.Slide.RegularSlide;
import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;
import Domain.Core.SlideShow;
import Domain.Core.Slide.TOCSlide;

public interface DomainVisitor {

    public void visitSlideShow(SlideShow slideShow);
    public void visitConcreteSlide(RegularSlide slide);
    public void visitTOCSlide(TOCSlide slide);
    public void visitTextItem(TextItem textItem);
    public void visitImageItem(ImageItem imageItem);
    public void visitListItem(List list);
    public void visitTableItem(Table table);

}
