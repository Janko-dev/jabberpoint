package Domain.Core;

import Domain.Core.Content.ImageItem;
import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Content.TextItem;

public interface Visitor {

    public void visitSlide(Slide slide);
    public void visitTextItem(TextItem textItem);
    public void visitImageItem(ImageItem imageItem);
    public void visitListItem(List list);
    public void visitTableItem(Table table);

}
