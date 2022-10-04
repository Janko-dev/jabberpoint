package Domain.Core;

public interface Iterable<T> {
    public Iterator<T> createIterator();
}
