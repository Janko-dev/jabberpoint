package Domain.Core.Iterator;

import Domain.Core.SlideShowComponent;

/**
 * Iterator interface that defines basic navigation through the iterator.
 * Note that this interface includes a previous method that is the opposite of the next method.
 * This is due to the fact that navigation within slide shows is not performed in one direction, but may be performed back-and-forward.
 * <p>
 * Also note that the current method returns a SlideShowComponent.
 * Considerations were made to use a generic type parameter {@code <T>}.
 * However, this was not needed, as every domain component can be represented as a {@code SlideShowComponent}.
 */
public interface Iterator {

    public void first();
    public void next();
    public void previous();
    public boolean isDone();
    public int getIndex();
    public SlideShowComponent current();
}
