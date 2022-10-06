package Domain.Services;

import Domain.Core.Slide;
import Infrastructure.Reader;
import Infrastructure.Writer;

public interface DomainServices {

    public void createSlideShowFrom(Reader reader, String filePath);
    public void saveSlideShowTo(Writer writer, String filePath);

    public void nextSlide();
    public void previousSlide();
    public Slide getCurrentSlide();
    public int getCurrentSlideIndex();
    public int getSlideShowLength();

}
