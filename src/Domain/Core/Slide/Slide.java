package Domain.Core.Slide;

import Domain.Core.SlideShowComposite;

/**
 * Abstract representation of slide that is a composite. 2 concrete classes extend this class:
 * - RegularSlide
 * - TOCSlide
 */
public abstract class Slide extends SlideShowComposite {

    public Slide(){
        super();
    }

    /**
     * Default implementation of getSubject that is convenient for not using type casts
     * @return an empty String
     */
    public String getSubject(){
        return "";
    }
}
