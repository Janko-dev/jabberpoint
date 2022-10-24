package Communication;

import Domain.Services.Facade.DomainServices;

/**
 * Next command responsible for navigating to the next slide in the slide show.
 */
public class NextCommand implements Command{

    public DomainServices receiver;

    public NextCommand(DomainServices receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.nextSlide();
    }
}
