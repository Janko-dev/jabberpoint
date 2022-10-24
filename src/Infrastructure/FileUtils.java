package Infrastructure;

import java.util.HashMap;

/**
 * File utilities containing static methods.
 */
public class FileUtils {

    public static final HashMap<String, Reader> formatToReader = new HashMap<String, Reader>(){{
        put("xml", new XMLReader());
    }};

    public static final HashMap<String, Writer> formatToWriter = new HashMap<String, Writer>(){{
        put("xml", new XMLWriter());
    }};

    /**
     * Private constructor guarantees non-instantiability of this class.
     */
    private FileUtils(){}

    /**
     * Convenience static method for extracting the file extension of a file path
     * for example: {@code "test.xml"} would return String {@code "xml"}
     * @param filePath The file path as a String
     * @return a substring of the provided file path containing the file extension.
     *         If path has no extension, then an empty string is returned.
     */
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
