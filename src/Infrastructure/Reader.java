package Infrastructure;

import java.io.File;

public abstract class Reader<T> {

    public abstract T readFile(String filePath);

}
