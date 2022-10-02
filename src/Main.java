import Controls.DomainDirector;
import Controls.SlideShowDirector;
import Domain.Core.SlideShow;
import Domain.Services.SlideShowBuilder;
import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {
        // main is controller/director for now

        DomainDirector<SlideShow, Node> director = new SlideShowDirector(new SlideShowBuilder());
        SlideShow result = director.make();

        System.out.println(result);
    }
}