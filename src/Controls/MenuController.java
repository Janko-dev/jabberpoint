package Controls;

import Communication.*;
import Domain.Services.Facade.DomainServices;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuController extends MenuBar implements Observable {

    private ArrayList<Observer> observers;
    private DomainServices services;

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

    private void addExitMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Exit");
        menuItem.addActionListener(e -> {
            notifyObservers(new ExitCommand());
        });
        menu.add(menuItem);
    }

    private void addPreviousViewMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Previous slide");
        menuItem.addActionListener(e -> {
            notifyObservers(new PreviousCommand(services));
        });
        menu.add(menuItem);
    }

    private void addNextViewMenuItem(Menu menu) {
        MenuItem menuItem = mkMenuItem("Next slide");
        menuItem.addActionListener(e -> {
            notifyObservers(new NextCommand(services));
        });
        menu.add(menuItem);
    }


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

    private MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Command command) {
        for (Observer ob : observers){
            ob.update(command);
        }
    }
}
