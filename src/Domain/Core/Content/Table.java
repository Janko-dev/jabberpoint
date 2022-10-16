package Domain.Core.Content;

import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

public class Table extends SlideShowComposite {

    private int rows, cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Table(int rows, int cols){
        super();
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitTableItem(this);
    }
}
