package Infrastructure;

import Domain.Services.Creation.Deserializer;

import java.io.File;

public abstract class Reader {

    public abstract Deserializer readFile(String filePath);
    public abstract Deserializer readFile(File file);
}
