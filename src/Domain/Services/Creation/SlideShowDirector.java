package Domain.Services.Creation;

import Domain.Core.SlideShow;
import Infrastructure.Reader;

/**
 * Responsible for directing the process of creating a {@code SlideShow}.
 */
public class SlideShowDirector extends DomainDirector<SlideShow>{

    public SlideShowDirector(DomainBuilder<SlideShow> builder, Reader reader){
        this.reader = reader;
        this.changeBuilder(builder);
    }

    /**
     * The following steps are taken to create the slide show.
     * The file path applied to the {@code readFile(String)} method of the reader yields a deserializer that acts as a factory for the domain.
     * The builder sets its converter to the yielded deserializer.
     * Thereafter, title, author, and date are deserialized and assembled in the builder.
     * Then, the content is assembled within the builder.
     * Finally, the build result is returned.
     * @param filePath a String containing the file path to be read and build.
     * @return the {@code SlideShow} result of the building process.
     */
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
