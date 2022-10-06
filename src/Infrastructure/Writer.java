package Infrastructure;

import Domain.Core.SlideShow;

import java.io.File;
import java.io.IOException;

public abstract class Writer {

    public abstract void writeToFile(String filePath, SlideShow slideShow);
    public abstract void writeToFile(File file, SlideShow slideShow);
}
