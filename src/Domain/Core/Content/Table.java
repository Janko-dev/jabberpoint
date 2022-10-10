package Domain.Core.Content;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Iterator.SlideShowIterator;
import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public class Table extends SlideShowComposite {

    private int rows, cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Table(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.components = new ArrayList<>();
    }
    @Override
    public Iterator createIterator() {
        return new SlideShowIterator(this.components);
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitTableItem(this);
    }
}
