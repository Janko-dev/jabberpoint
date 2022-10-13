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

    private DomainServices receiver;
    private String filePath;

    public OpenCommand(DomainServices services, String filePath){
        this.receiver = services;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        String extension = FileUtils.getExtension(filePath);
        Reader reader = formatToReader.get(extension);
        if (reader == null) return;
        receiver.createSlideShowFrom(filePath, reader);
    }
}
