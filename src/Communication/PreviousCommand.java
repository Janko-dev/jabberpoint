package Communication;

import Domain.Services.Facade.DomainServices;

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
