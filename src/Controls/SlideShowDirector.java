package Controls;

import Domain.Core.SlideShow;
import Domain.Services.Deserializable;
import Domain.Services.DomainBuilder;
import Infrastructure.Reader;

public class SlideShowDirector extends DomainDirector<SlideShow>{

    public SlideShowDirector(DomainBuilder<SlideShow> builder, Reader reader){
        this.reader = reader;
        this.changeBuilder(builder);
    }

    @Override
    public SlideShow make(String filePath) {
        Deserializable converter = reader.readFile(filePath);

        builder.reset();
        builder.setConverter(converter);

        builder.setTitle();
        builder.setAuthor();
        builder.setDate();
        builder.appendSlides();
        return builder.getResult();
    }
}
