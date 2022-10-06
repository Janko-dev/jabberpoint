package Domain.Services;

import Domain.Core.Slide;
import Domain.Core.SlideShow;

public class SlideShowBuilder implements DomainBuilder<SlideShow>{

    private SlideShow slideShow;
    private Deserializer converter;

    public SlideShowBuilder(){
        reset();
    }

    @Override
    public void reset() {
        this.slideShow = new SlideShow();
    }

    @Override
    public void setConverter(Deserializer converter) {
        this.converter = converter;
    }

    @Override
    public void setTitle() {
        this.slideShow.title = converter.parseTitle();
    }

    @Override
    public void setAuthor() {
        this.slideShow.author = converter.parseAuthor();
    }

    @Override
    public void setDate() {
        this.slideShow.date = converter.parseDate();
    }

    @Override
    public void appendContent() {
        for (int index = 0, len = converter.getSlidesLength(); index < len; index++){
            Slide newSlide = converter.convertToSlide(index);
            if (newSlide == null) return;
            this.slideShow.components.add(newSlide);
        }
    }

    @Override
    public SlideShow getResult() {
        return this.slideShow;
    }
}
