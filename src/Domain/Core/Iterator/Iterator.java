package Domain.Core.Iterator;

public interface Iterator<T> {

    public void first();
    public void next();
    public void previous();
    public boolean isDone();
    public int getIndex();
    public T current();
    public void setCurrent(int index);
}
