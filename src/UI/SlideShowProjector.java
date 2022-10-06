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

        KeyBoardController keyInput = new KeyBoardController(this);
        keyInput.addObserver(this);
        this.addKeyListener(keyInput);

        defaultFont = new Font("Helvetica", Font.PLAIN, 20);

        services = new DomainServicesFacade();
        services.createSlideShowFrom(new XMLReader(), "examples/slideshow_test.xml");

        initializeScreen();
        MenuController menuController = new MenuController(services, frame);
        menuController.addObserver(this);
        frame.setMenuBar(menuController);

//        services.saveSlideShowTo(new XMLWriter(), "resources/slideshow_test_out.xml");

    }

    public void drawNextSlide(){
        services.nextSlide();
    }

    public void drawPreviousSlide(){
        services.previousSlide();
    }

    @Override
    public void update(Command command) {
        command.execute();
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
        int fontHeight = getFontMetrics(defaultFont).getHeight();
        int fontWidth = defaultFont.getSize();
        int textOffset = COUNTER_OFFSET + fontWidth * slideCount.length();

        g.setColor(Color.BLACK);
        g.drawString(slideCount, COUNTER_OFFSET, frame.getHeight() - fontHeight - COUNTER_OFFSET);

        Rectangle boundingBox = new Rectangle(textOffset, 0, frame.getWidth()-textOffset, frame.getHeight());

        visitor = new RenderVisitor(g, boundingBox);
        Slide current = services.getCurrentSlide();
        current.accept(visitor);

        g.dispose();
    }

}
