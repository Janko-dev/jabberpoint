package Domain.Services.Creation;

import Domain.Core.Slide.Slide;

/**
 * The deserializer interface acts as the specification of deserializing the contents of a slide show.
 * That is, the meta information (title, author, date), and the individual slides.
 * Implementations of this interface should convert arbitrary data structures to domain specific data structures (e.g., xml to domain, or json to domain)
 */
public interface Deserializer {
    public Slide convertToSlide(int nodeIndex);
    public String parseTitle();
    public String parseAuthor();
    public String parseDate();
    public int getSlidesLength();
}
