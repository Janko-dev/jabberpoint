package Domain.Services.Creation;

import Domain.Core.Slide.Slide;
import Domain.Core.SlideShow;

/**
 * The concrete slide show builder implements the domain builder with a {@code SlideShow} as the generic parameter {@code <T>}
 * It abstracts over the deserializer to allow multiple deserialization strategies to produce the same resulting slide show.
 */
public class SlideShowBuilder implements DomainBuilder<SlideShow>{

    private SlideShow slideShow;
    private Deserializer converter;

    public SlideShowBuilder(){
        reset();
    }

    /**
     * resets the builder with a newly created slide show.
     */
    @Override
    public void reset() {
        this.slideShow = new SlideShow();
    }

    /**
     * Sets converted to supplied {@code Deserializer}.
     * @param converter an object implementing the {@code Deserializer} interface.
     */
    @Override
    public void setConverter(Deserializer converter) {
        this.converter = converter;
    }

    /**
     * Sets title of slide show by parsing the title with the converter.
     */
    @Override
    public void setTitle() {
        this.slideShow.setTitle(converter.parseTitle());
    }

    /**
     * Sets author of slide show by parsing the author with the converter.
     */
    @Override
    public void setAuthor() {
        this.slideShow.setAuthor(converter.parseAuthor());
    }

    /**
     * Sets date of slide show by parsing the date with the converter.
     */
    @Override
    public void setDate() {
        this.slideShow.setDate(converter.parseDate());
    }

    /**
     * Appends content by deserializing with the converter.
     * In this case, content can be interpreted as the set of slides.
     */
    @Override
    public void appendContent() {
        for (int index = 0, len = converter.getSlidesLength(); index < len; index++){
            Slide newSlide = converter.convertToSlide(index);
            if (newSlide == null) return;
            this.slideShow.addComponent(newSlide);
        }
    }

    /**
     * @return the slide show object that was constructed.
     */
    @Override
    public SlideShow getResult() {
        return this.slideShow;
    }
}
