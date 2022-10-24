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

/**
 * The concrete facade, which implements the domain services interface, and acts as an observer.
 * This class acts as an abstraction of several pieces of functionality within the architecture, in particular within the domain core.
 */
public class DomainServicesFacade implements DomainServices, Observer {

    private SlideShow slideShow;
    private Iterator slideShowIterator;
    private final String demoPath = "examples/slideshow_test.xml";

    /**
     * Constructor that is responsible for creating a slide show by using a demo slide show in xml format.
     */
    public DomainServicesFacade(){
        createSlideShowFrom(demoPath, new XMLReader());
    }

    /**
     * Creates a slideshow from a specified file path and a supplied reader.
     * A slide show builder is constructed and injected, together with the reader, to a slide show director, that orchestrates the assembling process of creating a slide show.
     * Thereafter, a slide show iterator is instantiated to be used for navigation.
     * @param filePath a String containing the file path to the slide show
     * @param reader an instance of a concrete {@code Reader} that matches the file format of the specified file path.
     */
    @Override
    public void createSlideShowFrom(String filePath, Reader reader) {
        SlideShowBuilder builder = new SlideShowBuilder();
        SlideShowDirector director = new SlideShowDirector(builder, reader);
        slideShow = director.make(filePath);
        slideShowIterator = slideShow.createIterator();
    }

    /**
     * Saves the slide show to a specified file path with a supplied writer.
     * The functionality is delegated to the {@code writeToFile(String, SlideShow)} method of the {@code Writer} class.
     * @param filePath a String containing the file path to be used to save the slide show to.
     * @param writer an instance of a concrete {@code Writer} that matches the file format of the specified file path.
     */
    @Override
    public void saveSlideShowTo(String filePath, Writer writer) {
        writer.writeToFile(filePath, slideShow);
    }

    /**
     * Changes to the next slide, if there is a next slide available.
     * If not, then the method does not perform a side effect.
     */
    @Override
    public void nextSlide() {
        if (slideShowIterator.getIndex() + 1 < slideShow.getLength())
            slideShowIterator.next();
    }

    /**
     * Changes to the previous slide, if there is a previous slide available.
     * If not, then navigation goes to the first slide.
     */
    @Override
    public void previousSlide() {
        if (slideShowIterator.getIndex() <= 0) {
            slideShowIterator.first();
        } else {
            slideShowIterator.previous();
        }
    }

    /**
     * @return the current slide as a {@code SlideShowComponent}
     */
    @Override
    public SlideShowComponent getCurrentSlide() {
        return slideShowIterator.current();
    }

    /**
     * @return the index of the current slide as an Integer.
     */
    @Override
    public int getCurrentSlideIndex() {
        return slideShowIterator.getIndex() + 1;
    }

    /**
     * @return The total amount of the slides in the slideshow.
     */
    @Override
    public int getSlideShowLength() {
        return slideShow.getComponents().size();
    }

    /**
     * @return A String representing the slide show meta information, i.e., title, author and date.
     */
    @Override
    public String getMetaString() {
        return slideShow.getTitle() + ": made by " + slideShow.getAuthor() + " on " + slideShow.getDate();
    }

    /**
     * @return A String representing the current slide out of the total amount of slides.
     */
    @Override
    public String getSlideCount() {
        return "slide " + getCurrentSlideIndex() + " of " + getSlideShowLength();
    }

    /**
     * implemented from the Observer interface and responsible for execution of command.
     * @param command an instance of the {@code Command} interface.
     */
    @Override
    public void update(Command command) {
        command.execute();
    }
}
