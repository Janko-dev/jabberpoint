package UI;

import Communication.Command;
import Controls.KeyBoardController;
import Controls.MenuController;
import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideShowComponent;
import Domain.Services.Facade.DomainServicesFacade;
import Domain.Services.Visitors.DomainRenderer;
import Domain.Services.Visitors.StyleRenderer;
import Infrastructure.XMLReader;

import java.awt.*;

public class SlideShowProjector extends Projector{

    private static final int TOP_OFFSET = 50;
    private static final Font defaultFont = new Font("Arial", Font.PLAIN, 20);
    private final DomainServicesFacade services;

    public SlideShowProjector(String title, int screenWidth, int screenHeight){
        super(title, screenWidth, screenHeight);

        services = new DomainServicesFacade();
        services.createSlideShowFrom(new XMLReader(), "examples/slideshow_test.xml");

        initializeScreen();

        KeyBoardController keyInput = new KeyBoardController(services);
        keyInput.addObserver(this);
        keyInput.addObserver(services);
        this.addKeyListener(keyInput);

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

        SlideShowComponent current = services.getCurrentSlide();

        DomainRenderer domainRenderer = new DomainRenderer(g, 0, TOP_OFFSET, frame.getBounds());
        current.accept(domainRenderer);

        g.setFont(defaultFont);
        g.setColor(Color.BLACK);

        String slideCount = services.getSlideCount();
        int textOffsetX = frame.getWidth() - (defaultFont.getSize() * slideCount.length());
        g.drawString(slideCount, textOffsetX, TOP_OFFSET/2);
        g.drawString(services.getMetaString(), TOP_OFFSET, TOP_OFFSET/2);

        g.dispose();
    }

}
