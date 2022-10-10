package Communication;

import Domain.Services.Facade.DomainServices;

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
