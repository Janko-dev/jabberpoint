package Domain.Core.Content;

import Domain.Core.SlideShowComposite;
import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete composite that holds a list of slide show components.
 * Defined in two dimensions with a rows and cols.
 */
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

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitTableItem(Table)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitTableItem(this);
    }
}
