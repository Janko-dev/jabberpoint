import Controls.DomainDirector;
import Controls.SlideShowDirector;
import Domain.Core.SlideShow;
import Domain.Services.SlideShowBuilder;
import Domain.Services.DOMDeserializer;
import Infrastructure.Reader;
import Infrastructure.XMLReader;

public class Main {
    public static void main(String[] args) {
        // main is controller/director for now

        SlideShowBuilder builder = new SlideShowBuilder();
        Reader xmlReader = new XMLReader();
        DomainDirector<SlideShow> director = new SlideShowDirector(builder, xmlReader);
        SlideShow result = director.make("resources/slideshow_test.xml");

        System.out.println(result);
    }
}