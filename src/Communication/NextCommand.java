package Communication;

import UI.Projector;
import UI.SlideShowProjector;

public class NextCommand implements Command{

    public SlideShowProjector receiver;

    public NextCommand(SlideShowProjector receiver){
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
