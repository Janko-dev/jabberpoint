package Domain.Core;

import Domain.Core.Style.Style;
import Domain.Services.Visitors.DomainVisitor;

import java.util.ArrayList;

public abstract class SlideShowComponent {

    private ArrayList<Style> styles = new ArrayList<>();

    public void addStyle(Style style){
        styles.add(style);
    }

    public Style getStyle(int index){
        return styles.get(index);
    }

    public void removeStyle(Style style){
        styles.remove(style);
    }

    public ArrayList<Style> getStyles(){
        return styles;
    }

    public abstract void accept(DomainVisitor v);

}
