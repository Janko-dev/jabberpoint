package Domain.Services.Creation;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Slide;
import Domain.Core.SlideShow;

import java.util.HashMap;

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
        this.slideShow.setTitle(converter.parseTitle());
    }

    @Override
    public void setAuthor() {
        this.slideShow.setAuthor(converter.parseAuthor());
    }

    @Override
    public void setDate() {
        this.slideShow.setDate(converter.parseDate());
    }

    @Override
    public void appendContent() {
        for (int index = 0, len = converter.getSlidesLength(); index < len; index++){
            Slide newSlide = converter.convertToSlide(index);
            if (newSlide == null) return;
            this.slideShow.getComponents().add(newSlide);
        }
    }

    @Override
    public SlideShow getResult() {
        return this.slideShow;
    }
}
