package Controls;

import Communication.*;
import Domain.Services.Facade.DomainServices;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Concrete menu controller responsible for managing menu buttons and notifying any interested objects.
 * This class extends {@code java.awt.MenuBar} and implements {@code Observable}.
 * The controller is able to send messages in the form of commands by notifying observers based on action events.
 */
public class MenuController extends MenuBar implements Publisher {

    private static final long serialVersionUID = 2838866946741739447L;
    private ArrayList<Observer> observers;
    private DomainServices services;

    /**
     * Constructor that constructs all menus and corresponding buttons with specific functionality.
     * @param services A concrete implementation of the {@code DomainServices} interface.
     */
    public MenuController(DomainServices services){
        this.services = services;
        observers = new ArrayList<>();
        // File menu: Open, Save
        Menu fileMenu = new Menu("File");
        addOpenMenuItem(fileMenu);
        fileMenu.addSeparator();
        addSaveMenuItem(fileMenu);
        add(fileMenu);

        // View menu: Next slide, Previous slide, Exit
        Menu viewMenu = new Menu("View");
        addNextViewMenuItem(viewMenu);
        viewMenu.addSeparator();
        addPreviousViewMenuItem(viewMenu);
        viewMenu.addSeparator();
        addExitMenuItem(viewMenu);
        add(viewMenu);

        // Help menu: About box
        Menu helpMenu = new Menu("Help");
        addAboutBox(helpMenu);
        add(helpMenu);
    }

    /**
     * Adds an about button to the menu supplied as parameter.
     * The button, when pressed, shows a dialog box with general information about the application.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addAboutBox(Menu menu) {
        MenuItem menuItem = mkMenuItem("About");
        menuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(new Frame(),
                    "Jabberpoint is a slide show presentation tool developed for the master course Design for Change at the Open University. \n" +
                            "The project is meant to demonstrate the process of creating software in a future-proof way, \n" +
                            "by using an agreed upon ubiquitous language, applying common Object-Oriented design patterns, \n" +
                            "and following several design principles.\n",
                    "About JabberPoint",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        menu.add(menuItem);
    }

    /**
     * Adds an exit button to the menu supplied as parameter, that when pressed, shuts down the application.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addExitMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Exit");
        menuItem.addActionListener(e -> {
            notifyObservers(new ExitCommand());
        });
        menu.add(menuItem);
    }

    /**
     * Adds a previous slide button to the menu supplied as parameter,
     * that when pressed, advances the slideshow to the previous slide.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addPreviousViewMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Previous slide");
        menuItem.addActionListener(e -> {
            notifyObservers(new PreviousCommand(services));
        });
        menu.add(menuItem);
    }

    /**
     * Adds a next slide button to the menu supplied as parameter,
     * that when pressed, advances the slideshow to the next slide.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addNextViewMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Next slide");
        menuItem.addActionListener(e -> {
            notifyObservers(new NextCommand(services));
        });
        menu.add(menuItem);
    }

    /**
     * Adds an open button to the menu supplied as parameter,
     * that when pressed, opens a file explorer that lets the used select which file to open.
     * A new open command is instantiated with the selected file path.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addOpenMenuItem(Menu menu){
        MenuItem menuItem = mkMenuItem("Open");
        menuItem.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(new Frame(), "Pick a file to load", FileDialog.LOAD);
            fileDialog.setDirectory(System.getProperty("user.dir"));
            fileDialog.setVisible(true);
            String filePath = fileDialog.getDirectory() + fileDialog.getFile();
            if (filePath != null) {
                notifyObservers(new OpenCommand(services, filePath));
            }
        });
        menu.add(menuItem);
    }

    /**
     * Adds a save button to the menu supplied as parameter,
     * that when pressed, opens a file explorer that lets the used save the slideshow at a file path.
     * A new save command is instantiated with the selected file path.
     * @param menu The menu instance of type {@code java.awt.Menu} that will be appended a new menu item.
     */
    private void addSaveMenuItem(Menu menu){
        MenuItem menuItem = mkMenuItem("Save");
        menuItem.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(new Frame(), "Save as", FileDialog.SAVE);
            fileDialog.setDirectory(System.getProperty("user.dir"));
            fileDialog.setFile("*.xml");
            fileDialog.setVisible(true);
            String filePath = fileDialog.getDirectory() + fileDialog.getFile();
            if (filePath != null) {
                notifyObservers(new SaveCommand(services, filePath));
            }
        });
        menu.add(menuItem);
    }

    /**
     * Convenient method for creating menu items.
     * @param name name of the menu item as a String
     * @return an instance of {@code java.awt.MenuItem}
     */
    private MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }


    /**
     * Method to add an observer to this observable
     * @param observer A concrete implementation of the {@code Observer} interface.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Method to remove an observer from this observable
     * @param observer A concrete implementation of the {@code Observer} interface.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers with a provided command by calling the update method.
     * @param command an instance of {@code Command}
     */
    @Override
    public void notifyObservers(Command command) {
        for (Observer ob : observers){
            ob.update(command);
        }
    }
}
