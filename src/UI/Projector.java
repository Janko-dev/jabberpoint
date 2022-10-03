package UI;

import Communication.Observer;
import Controls.Direction;

public class Projector implements Observer<Direction> {
    @Override
    public void update(Direction state) {
        // based on state value, move to next or previous slide
    }
}
