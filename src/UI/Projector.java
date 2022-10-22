package UI;

import Communication.Observer;
import javax.swing.*;
import java.awt.*;


/**
 * Projector is the abstract base class that is responsible for maintaining and initializing the window for the purpose of
 * displaying a slide show.
 * It extends the Canvas class found in java.awt and implements the Observer interface. Inherited classes
 */
public abstract class Projector extends Canvas implements Observer {

    protected static final Font defaultFont = new Font("Arial", Font.ITALIC|Font.BOLD, 20);
    protected static final int TOP_OFFSET = 50;
    protected String title;
    protected int screenWidth, screenHeight;
    protected JFrame frame;

    /**
     * @param title title of the window
     * @param screenWidth width of the window in pixels
     * @param screenHeight height of the window in pixels
     */
    public Projector(String title, int screenWidth, int screenHeight){
        this.title = title;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Initializes the window by instantiating a JFrame with the following predefined configurations.
     * <ul>
     *     <li>Default close operation is set to default operating system application exiting</li>
     *     <li>Window size is set as minimum with {@code Dimension(screenWidth, screenHeight)}, which makes it scalable</li>
     *     <li>Window is spawned in the middle of the screen</li>
     *     <li>Window is attempted to focus on</li>
     * </ul>
     * Afterwards, abstract start method is called.
     */
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

    /**
     * Implementation of this method ensures execution of code after the initial setup of the JFrame is completed.
     */
    public abstract void start();
}
