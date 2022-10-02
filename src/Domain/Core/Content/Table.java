package Domain.Core.Content;

import Domain.Core.SlideShowComposite;

import java.util.ArrayList;
import java.util.Iterator;

public class Table extends SlideShowComposite {

    private int rows, cols;

    public Table(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.components = new ArrayList<>();
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}
