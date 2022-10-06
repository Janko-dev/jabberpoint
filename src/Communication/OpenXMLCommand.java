package Communication;

import Domain.Services.DomainServices;
import Infrastructure.XMLReader;

import java.io.File;

public class OpenXMLCommand implements Command{

    private DomainServices services;
    private String filePath;

    public OpenXMLCommand(DomainServices services, String filePath){
        this.services = services;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        services.createSlideShowFrom(new XMLReader(), filePath);
    }
}
