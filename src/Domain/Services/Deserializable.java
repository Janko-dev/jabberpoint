package Domain.Services;

import Domain.Core.Content.List;
import Domain.Core.Content.Table;
import Domain.Core.Slide;
import Domain.Core.SlideShowComponent;

public interface Deserializable {
    public Slide convertToSlide(int nodeIndex);
    public String parseTitle();
    public String parseAuthor();
    public String parseDate();
    public int getSlidesLength();
}
