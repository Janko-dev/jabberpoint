package Infrastructure;

import Domain.Services.Deserializable;

public abstract class Reader {

    public abstract Deserializable readFile(String filePath);

}
