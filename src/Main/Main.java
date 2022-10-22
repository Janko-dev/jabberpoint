package Main;

import UI.SlideShowProjector;

/**
 * Starting point of Jabberpoint Application
 */
public class Main {
    /**
     * Main.Main method
     * Instantiates the slideshow projector
     * @param args Command-line arguments (are not used in this application)
     */
    public static void main(String[] args) {
        new SlideShowProjector("Jabberpoint", 900, 600);
    }
}