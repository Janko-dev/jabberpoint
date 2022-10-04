package UI;

import Communication.Command;
import Communication.Observer;
import Controls.KeyBoardController;
import Domain.Core.SlideShow;

import javax.swing.*;
import java.awt.*;

public abstract class Projector extends Canvas implements Observer {

    protected String title;
    protected int screenWidth, screenHeight;
    protected JFrame frame;

    public Projector(String title, int screenWidth, int screenHeight){
        this.title = title;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    protected void initializeScreen(){
        Dimension dim = new Dimension(screenWidth, screenHeight);
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(dim);
        frame.setMinimumSize(dim);
        frame.setMaximumSize(dim);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
        this.createBufferStrategy(3);
        start();
    }

    public abstract void start();
}
