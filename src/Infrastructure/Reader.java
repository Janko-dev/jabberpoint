package Infrastructure;

import Domain.Services.Deserializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Reader {

    public abstract Deserializer readFile(String filePath);
    public abstract Deserializer readFile(File file);
}
