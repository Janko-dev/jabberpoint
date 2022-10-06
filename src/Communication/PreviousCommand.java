package Communication;

import UI.Projector;
import UI.SlideShowProjector;

public class PreviousCommand implements Command{
    public SlideShowProjector receiver;

    public PreviousCommand(SlideShowProjector receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.drawPreviousSlide();
    }

}
