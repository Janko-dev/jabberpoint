package Domain.Services;

import Domain.Core.Iterator.Iterator;
import Domain.Core.Slide;
import Domain.Core.SlideShow;
import Infrastructure.Reader;
import Infrastructure.Writer;

import java.io.IOException;

public class DomainServicesFacade implements DomainServices{

    private SlideShow slideShow;
    private Iterator<Slide> slideShowIterator;

    @Override
    public void createSlideShowFrom(Reader reader, String filePath) {
        SlideShowBuilder builder = new SlideShowBuilder();
        SlideShowDirector director = new SlideShowDirector(builder, reader);
        slideShow = director.make(filePath);
        slideShowIterator = slideShow.createIterator();
    }

    @Override
    public void saveSlideShowTo(Writer writer, String filePath) {
        try {
            writer.writeToFile(filePath, slideShow);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void nextSlide() {
        if (!slideShowIterator.isDone())
            slideShowIterator.next();
    }

    @Override
    public void previousSlide() {
        if (slideShowIterator.getIndex() <= 0) {
            slideShowIterator.first();
        } else {
            slideShowIterator.previous();
        }
    }

    @Override
    public Slide getCurrentSlide() {
        return slideShowIterator.current();
    }

    @Override
    public int getCurrentSlideIndex() {
        return slideShowIterator.getIndex() + 1;
    }

    @Override
    public int getSlideShowLength() {
        return slideShow.components.size();
    }
}
