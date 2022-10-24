package Domain.Core;

import Domain.Core.Style.Style;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

/**
 * Abstract base class of all domain components.
 * The SlideShowComponent acts as a {@code Visitable} interface for the entire inheritance tree.
 * The commonality that all domain components have is the styling, which is also represented in this base class.
 */
public abstract class SlideShowComponent {

    private final ArrayList<Style> styles = new ArrayList<>();

    /**
     * Add a provided style to the list of styles.
     * @param style an instance of {@code Style} interface.
     */
    public void addStyle(Style style){
        styles.add(style);
    }

    /**
     * Retrieve style based on index.
     * @param index integer index into the styles list.
     * @return a {@code Style} at the specified index in list of styles.
     */
    public Style getStyle(int index){
        return styles.get(index);
    }

    /**
     * Remove a style
     * @param style a {@code Style} to remove from the slide show component.
     */
    public void removeStyle(Style style){
        styles.remove(style);
    }

    /**
     * @return the list of styles.
     */
    public ArrayList<Style> getStyles(){
        return styles;
    }

    public abstract void accept(DomainVisitor v);

}
