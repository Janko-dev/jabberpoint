package UI;

import Communication.Command;
import Controls.KeyBoardController;
import Controls.MenuController;
import Domain.Core.SlideShowComponent;
import Domain.Services.Facade.DomainServicesFacade;
import Domain.Services.Visitors.DomainRenderer;
import Domain.Services.Visitors.DomainVisitor;
import Infrastructure.XMLReader;

import java.awt.*;

public class SlideShowProjector extends Projector{
    private final DomainServicesFacade services;

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

    @Override
    public void update(Command command) {
        repaint();
    }

    @Override
    public void start() {
        repaint();
    }

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
