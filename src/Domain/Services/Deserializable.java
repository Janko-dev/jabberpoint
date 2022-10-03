package Domain.Services;

import Domain.Core.Slide;

public interface Deserializable {
    public Slide convertToSlide(int nodeIndex);
    public String parseTitle();
    public String parseAuthor();
    public String parseDate();
    public int getSlidesLength();
}
