package Infrastructure;

import Domain.Core.SlideShow;

import java.io.IOException;

public abstract class Writer {

    public abstract void writeToFile(String filePath, SlideShow slideShow) throws IOException;
}
