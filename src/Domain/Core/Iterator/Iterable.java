package Domain.Core.Iterator;

public interface Iterable<T> {
    public Iterator<T> createIterator();
}
