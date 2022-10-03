package Communication;

import UI.Projector;

public class NextCommand implements Command{

    public Projector receiver;

    public NextCommand(Projector receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.drawNextSlide();
    }

    @Override
    public void unexecute() {
        this.receiver.drawPreviousSlide();
    }
}
