package Domain.Services.Facade;

import Domain.Core.SlideShowComponent;
import Infrastructure.Reader;
import Infrastructure.Writer;

/**
 * The contractual interface which exposes domain specific functionality through methods to the outer layers.
 * The methods in this interface can be considered as the services of the domain, and should be implemented by a facade.
 * We are aware that this list could be extended with a visitor rendering method. However, this would clutter the concrete facade implementing this interface with Graphics-specific jargon.
 */
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
