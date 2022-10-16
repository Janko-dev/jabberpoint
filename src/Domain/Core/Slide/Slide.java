package Domain.Core.Slide;

import Domain.Core.SlideShowComposite;

public abstract class Slide extends SlideShowComposite {

    public Slide(){
        super();
    }

    public String getSubject(){
        return "";
    }
}
