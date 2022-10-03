package Infrastructure;

import Domain.Services.Deserializable;

import java.io.File;

public abstract class Reader {

    public abstract Deserializable readFile(String filePath);

}
