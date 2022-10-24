package Communication;

import Domain.Services.Facade.DomainServices;

/**
 * Previous command responsible for navigating to the previous slide in the slide show.
 */
public class PreviousCommand implements Command{
    public DomainServices receiver;

    public PreviousCommand(DomainServices receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.previousSlide();
    }

}
