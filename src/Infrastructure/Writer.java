package Infrastructure;

public abstract class Writer<T> {

    public abstract void writeToFile(String filePath, T document);
}
