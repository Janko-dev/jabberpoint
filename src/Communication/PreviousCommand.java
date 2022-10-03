package Communication;

import UI.Projector;

public class PreviousCommand implements Command{
    public Projector receiver;

    public PreviousCommand(Projector receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.drawPreviousSlide();
    }

    @Override
    public void unexecute() {
        this.receiver.drawNextSlide();
    }
}
