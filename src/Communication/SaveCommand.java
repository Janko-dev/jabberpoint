package Communication;

import Domain.Services.Facade.DomainServices;
import Infrastructure.*;

/**
 * Defines the Save command for saving a slide show to a file
 */
public class SaveCommand implements Command {

    private DomainServices receiver;
    private String filePath;

    public SaveCommand(DomainServices services, String filePath){
        this.receiver = services;
        this.filePath = filePath;
    }

    /**
     * Determines the file extension, and based on that tries to get the writer that corresponds with the file extension.
     * The {@code FileUtils} class is used for this.
     * If there is no writer corresponding to the file format, then return.
     * Otherwise, save the slide show to a file path using the writer with the domain services facade.
     */
    @Override
    public void execute() {
        String extension = FileUtils.getExtension(filePath);
        Writer writer = FileUtils.formatToWriter.get(extension);
        if (writer == null) return;
        receiver.saveSlideShowTo(filePath, writer);
    }
}
