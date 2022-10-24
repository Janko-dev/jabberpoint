package Communication;

import Domain.Services.Facade.DomainServices;
import Infrastructure.FileUtils;
import Infrastructure.Reader;

/**
 * Defines the Open command for opening a slide show within Jabberpoint
 */
public class OpenCommand implements Command{

    private final DomainServices receiver;
    private final String filePath;

    public OpenCommand(DomainServices services, String filePath){
        this.receiver = services;
        this.filePath = filePath;
    }

    /**
     * Determines the file extension, and based on that tries to get the reader that corresponds with the file extension.
     * The {@code FileUtils} class is used for this.
     * If there is no reader corresponding to the file format, then return.
     * Otherwise, create a new slide show from file path and reader with the domain services facade.
     */
    @Override
    public void execute() {
        String extension = FileUtils.getExtension(filePath);
        Reader reader = FileUtils.formatToReader.get(extension);
        if (reader == null) return;
        receiver.createSlideShowFrom(filePath, reader);
    }
}
