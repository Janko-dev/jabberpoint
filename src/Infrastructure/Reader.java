package Infrastructure;

import Domain.Services.Deserializer;

public abstract class Reader {

    public abstract Deserializer readFile(String filePath);

}
