package Domain.Core.Iterator;

/**
 * Iterable interface which defines a way to instantiate an iterator.
 * Within the domain, the slide show composite is iterable.
 */
public interface Iterable {
    public Iterator createIterator();
}
