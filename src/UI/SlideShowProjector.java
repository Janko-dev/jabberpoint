package UI;

import Communication.Command;
import Controls.KeyBoardController;
import Controls.MenuController;
import Domain.Core.SlideShowComponent;
import Domain.Services.Facade.DomainServicesFacade;
import Domain.Services.Visitors.DomainRenderer;
import Domain.Services.Visitors.DomainVisitor;

import java.awt.*;

/**
 * Concrete SlideShow projector that is responsible for the graphical user interface of Jabberpoint.
 * Is dependent on the {@code DomainServicesFacade} by which the UI is able to access domain services.
 */
public class SlideShowProjector extends Projector{

	private final DomainServicesFacade services;

    /**
     * Constructor that is responsible for the initialization of the window through the Projector abstract class,
     * creation of the domain services facade, and controller setups.
     * @param title String type
     * @param screenWidth Integer type
     * @param screenHeight Integer type
     */
    public SlideShowProjector(String title, int screenWidth, int screenHeight){
        super(title, screenWidth, screenHeight);

        // create and bind domain services
        services = new DomainServicesFacade();

        // initialize JFrame to predefined settings
        initializeScreen();

        // keyboard controller observable setup
        KeyBoardController keyInput = new KeyBoardController(services);
        keyInput.addObserver(this);
        keyInput.addObserver(services);
        this.addKeyListener(keyInput);

        // menu controller observable setup
        MenuController menuController = new MenuController(services);
        menuController.addObserver(this);
        menuController.addObserver(services);
        frame.setMenuBar(menuController);
    }

    /**
     * Gets called on every notification action of the observable controllers that are bound to the slide show projector.
     * That is the Keyboard controller and the Menu controller.
     * Implementation disregards the param {@code Command} and calls the {@code repaint()} method to update the JFrame.
     * @param command Not used in this observer
     */
    @Override
    public void update(Command command) {
        repaint();
    }

    /**
     * Is the starting point when the window is finished initializing.
     * The start of the projector is delegated to {@code repaint()} method.
     */
    @Override
    public void start() {
        repaint();
    }

    /**
     * Override of the {@code paint(Graphics)} method in {@code Canvas}.
     * Uses the domain services to obtain the current slide that needs to be rendered.
     * A domain renderer is instantiated that visits the slide composite.
     * Meta information about the slideshow is obtained through the domain services, and rendered to the screen.
     * @param g the specified Graphics context
     */
    @Override
    public void paint(Graphics g){
        // get current slide
        SlideShowComponent current = services.getCurrentSlide();

        // visit and render slide items
        DomainVisitor domainRenderer = new DomainRenderer(g, 0, TOP_OFFSET, frame.getBounds(), defaultFont);
        current.accept(domainRenderer);

        // reset font to default
        g.setFont(defaultFont);
        g.setColor(Color.BLACK);

        // draw slide meta information
        String slideCount = services.getSlideCount();
        int textOffsetX = frame.getWidth() - (defaultFont.getSize() * slideCount.length());
        g.drawString(slideCount, textOffsetX, TOP_OFFSET/2);
        g.drawString(services.getMetaString(), TOP_OFFSET, TOP_OFFSET/2);

        // dispose graphics object and its context
        g.dispose();
    }

}
