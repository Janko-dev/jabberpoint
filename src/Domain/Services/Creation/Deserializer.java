package Domain.Services.Creation;

import Domain.Core.Slide;

public interface Deserializer {
    public Slide convertToSlide(int nodeIndex);
    public String parseTitle();
    public String parseAuthor();
    public String parseDate();
    public int getSlidesLength();
}
