package Domain.Services.Facade;

import Communication.Command;
import Communication.Observer;
import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideShow;
import Domain.Core.SlideShowComponent;
import Domain.Services.Creation.SlideShowBuilder;
import Domain.Services.Creation.SlideShowDirector;
import Infrastructure.Reader;
import Infrastructure.Writer;
import Infrastructure.XMLReader;

public class DomainServicesFacade implements DomainServices, Observer {

    private SlideShow slideShow;
    private Iterator slideShowIterator;
    private final String demoPath = "examples/slideshow_test.xml";

    public DomainServicesFacade(){
        createSlideShowFrom(demoPath, new XMLReader());
    }

    @Override
    public void createSlideShowFrom(String filePath, Reader reader) {
        SlideShowBuilder builder = new SlideShowBuilder();
        SlideShowDirector director = new SlideShowDirector(builder, reader);
        slideShow = director.make(filePath);
        slideShowIterator = slideShow.createIterator();
    }

    @Override
    public void saveSlideShowTo(String filePath, Writer writer) {
        writer.writeToFile(filePath, slideShow);
    }

    @Override
    public void nextSlide() {
        if (slideShowIterator.getIndex() + 1 < slideShow.getLength())
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
    public SlideShowComponent getCurrentSlide() {
        return slideShowIterator.current();
    }

    @Override
    public int getCurrentSlideIndex() {
        return slideShowIterator.getIndex() + 1;
    }

    @Override
    public int getSlideShowLength() {
        return slideShow.getComponents().size();
    }

    @Override
    public String getMetaString() {
        return slideShow.getTitle() + ": made by " + slideShow.getAuthor() + " on " + slideShow.getDate();
    }

    @Override
    public String getSlideCount() {
        return "slide " + getCurrentSlideIndex() + " of " + getSlideShowLength();
    }

    @Override
    public void update(Command command) {
        command.execute();
    }
}
