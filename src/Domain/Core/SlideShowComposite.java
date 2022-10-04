package Domain.Core;

import java.util.ArrayList;

public abstract class SlideShowComposite extends SlideShowComponent {

    public ArrayList<SlideShowComponent> components;

    public int getLength(){
        return components.size();
    }
}
