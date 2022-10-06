package Domain.Services;

import Domain.Core.SlideShow;
import Infrastructure.Reader;

public class SlideShowDirector extends DomainDirector<SlideShow>{

    public SlideShowDirector(DomainBuilder<SlideShow> builder, Reader reader){
        this.reader = reader;
        this.changeBuilder(builder);
    }

    @Override
    public SlideShow make(String filePath) {
        Deserializer converter = reader.readFile(filePath);

        builder.reset();
        builder.setConverter(converter);

        builder.setTitle();
        builder.setAuthor();
        builder.setDate();
        builder.appendContent();
        return builder.getResult();
    }
}
