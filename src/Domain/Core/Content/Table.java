package Domain.Core.Content;

import Domain.Core.Iterator;
import Domain.Core.SlideShowComposite;
import Domain.Core.Visitor;

import java.util.ArrayList;

public class Table extends SlideShowComposite {

    public int rows, cols;

    public Table(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.components = new ArrayList<>();
    }
    @Override
    public Iterator<List> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitTableItem(this);
    }
}
