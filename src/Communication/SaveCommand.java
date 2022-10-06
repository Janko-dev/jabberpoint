package Communication;

import Domain.Services.DomainServices;
import Infrastructure.*;

import java.util.HashMap;

public class SaveCommand implements Command {

    private static final HashMap<String, Writer> formatToWriter = new HashMap<String, Writer>(){{
        put("xml", new XMLWriter());
    }};

    private DomainServices services;
    private String filePath;

    public SaveCommand(DomainServices services, String filePath){
        this.services = services;
        this.filePath = filePath;
    }
    @Override
    public void execute() {
        String extension = FileUtils.getExtension(filePath);
        Writer writer = formatToWriter.get(extension);
        if (writer == null) return;
        services.saveSlideShowTo(writer, filePath);
    }
}
