package UI;

import Communication.Command;
import Communication.Observer;

import javax.swing.*;

public class Projector extends JFrame implements Observer {



    public void drawNextSlide(){

    }

    public void drawPreviousSlide(){

    }

    @Override
    public void update(Command command) {
        // based on state value, move to next or previous slide
        command.execute();
    }
}
