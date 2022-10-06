package Infrastructure;

import java.io.File;
import java.util.HashMap;

public class FileUtils {

    public static HashMap<String, Reader> toReader = new HashMap<String, Reader>() {{
        put(".xml", new XMLReader());
    }};

    public static String getExtension(File file) {
        String filePath = file.toString();
        int indexDot = file.toString().lastIndexOf('.');
        int p = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
        if (indexDot > p) {
            return filePath.substring(indexDot+1);
        } else {
            return ""; // In this case the file have an extension
        }
    }
}
