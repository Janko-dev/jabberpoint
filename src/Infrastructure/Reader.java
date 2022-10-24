package Infrastructure;

import Domain.Services.Creation.Deserializer;

import java.io.File;

/**
 * Abstract Reader class
 */
public abstract class Reader {

    /**
     * Reads file given a file path and returns a {@code Deserializer} object.
     * @param filePath file path is provided as a String.
     * @return An object that has implemented the {@code Deserializer} interface.
     */
    public abstract Deserializer readFile(String filePath);

    /**
     * Reads file given a {@code java.io.File} object and returns a {@code Deserializer} object.
     * @param file a {@code java.io.File} object is provided.
     * @return An object that has implemented the {@code Deserializer} interface.
     */
    public abstract Deserializer readFile(File file);
}
