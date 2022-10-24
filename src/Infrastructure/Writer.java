package Infrastructure;

import Domain.Core.SlideShow;

import java.io.File;

/**
 * Abstract Writer class
 */
public abstract class Writer {

    /**
     * Writes slide show object to a file given a file path.
     * @param filePath file path as a string
     * @param slideShow the slideshow object
     */
    public abstract void writeToFile(String filePath, SlideShow slideShow);

    /**
     * Writes slide show object to a file given a {@code java.io.File} object.
     * @param file file as a {@code java.io.File} object
     * @param slideShow the slideshow object
     */
    public abstract void writeToFile(File file, SlideShow slideShow);
}
