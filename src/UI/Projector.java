package UI;

import Communication.Observer;
import javax.swing.*;
import java.awt.*;

public abstract class Projector extends Canvas implements Observer {

    protected static final Font defaultFont = new Font("Arial", Font.ITALIC|Font.BOLD, 20);
    protected static final int TOP_OFFSET = 50;
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
        start();
    }

    public abstract void start();
}
