package Communication;

import Domain.Services.Facade.DomainServices;
import Infrastructure.FileUtils;
import Infrastructure.Reader;
import Infrastructure.XMLReader;

import java.util.HashMap;

public class OpenCommand implements Command{

    private static final HashMap<String, Reader> formatToReader = new HashMap<String, Reader>(){{
        put("xml", new XMLReader());
    }};

    private DomainServices services;
    private String filePath;

    public OpenCommand(DomainServices services, String filePath){
        this.services = services;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        String extension = FileUtils.getExtension(filePath);
        Reader reader = formatToReader.get(extension);
        if (reader == null) return;
        services.createSlideShowFrom(reader, filePath);
    }
}
