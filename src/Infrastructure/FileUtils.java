package Infrastructure;

import java.io.File;

public class FileUtils {

    public static String getExtension(File file) {
        String filePath = file.toString();
        return getExtension(filePath);
    }

    public static String getExtension(String filePath) {
        int indexOfDot = filePath.lastIndexOf('.');
        int lastSlash = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
        if (indexOfDot > lastSlash) {
            return filePath.substring(indexOfDot+1);
        } else {
            return ""; // In this case the file has no extension
        }
    }
}
