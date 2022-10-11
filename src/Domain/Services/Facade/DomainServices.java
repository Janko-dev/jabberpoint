package Domain.Services.Facade;

import Domain.Core.SlideShowComponent;
import Infrastructure.Reader;
import Infrastructure.Writer;

public interface DomainServices{

    public void createSlideShowFrom(String filePath, Reader reader);
    public void saveSlideShowTo(String filePath, Writer writer);

    public void nextSlide();
    public void previousSlide();
    public SlideShowComponent getCurrentSlide();
    public int getCurrentSlideIndex();
    public int getSlideShowLength();

    public String getMetaString();
    public String getSlideCount();

}
