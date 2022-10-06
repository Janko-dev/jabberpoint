package UI;

import Communication.Command;
import Controls.KeyBoardController;
import Controls.MenuController;
import Domain.Core.Slide;
import Domain.Core.SlideShow;
import Domain.Services.DomainServicesFacade;
import Domain.Services.RenderVisitor;
import Infrastructure.XMLReader;
import Infrastructure.XMLWriter;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class SlideShowProjector extends Projector{

    private static final int COUNTER_OFFSET = 20;
    private DomainServicesFacade services;
    private static Font defaultFont;
    private RenderVisitor visitor;

    public SlideShowProjector(String title, int screenWidth, int screenHeight){
        super(title, screenWidth, screenHeight);
        defaultFont = new Font("Arial", Font.PLAIN, 20);

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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        String slideCount = services.getCurrentSlideIndex() + "/" + services.getSlideShowLength();

        g.setFont(defaultFont);
        int slideCountY = getBounds().height - g.getFontMetrics().getHeight() - COUNTER_OFFSET;
        int fontWidth = defaultFont.getSize();
        int textOffset = COUNTER_OFFSET + fontWidth * slideCount.length();

        g.setColor(Color.BLACK);
        g.drawString(slideCount, COUNTER_OFFSET, slideCountY);

        Rectangle boundingBox = new Rectangle(textOffset, 0, frame.getWidth()-textOffset, frame.getHeight());

        visitor = new RenderVisitor(g, boundingBox);
        Slide current = services.getCurrentSlide();
        current.accept(visitor);

        g.dispose();
    }

}
