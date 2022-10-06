package Domain.Core.Content;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideIterator;
import Domain.Core.SlideShowComponent;
import Domain.Core.SlideShowComposite;
import Domain.Services.Visitor;

import java.util.ArrayList;

public class Table extends SlideShowComposite {

    public int rows, cols;

    public Table(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.components = new ArrayList<>();
    }
    @Override
    public Iterator<SlideShowComponent> createIterator() {
        return new SlideIterator(this.components);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTableItem(this);
    }
}
