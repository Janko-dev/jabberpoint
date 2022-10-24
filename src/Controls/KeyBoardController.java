package Controls;

import Communication.*;
import Domain.Services.Facade.DomainServices;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Concrete keyboard controller responsible for managing keystrokes and notifying any interested objects.
 * This class extends {@code java.awt.event.KeyAdapter} and implements {@code Observable}.
 * The controller is able to send messages in the form of commands by notifying observers based on key events.
 */
public class KeyBoardController extends KeyAdapter implements Observable {

    private ArrayList<Observer> observers;
    private DomainServices services;

    /**
     * Constructor that needs access to the facade, so that new commands can be injected with the facade.
     * @param services A concrete implementation of the {@code DomainServices} interface.
     */
    public KeyBoardController(DomainServices services){
        observers = new ArrayList<>();
        this.services = services;
    }

    /**
     * Checks what keys were pressed and fires new commands based on it.
     * keys: page down, down, enter, space, right, and "+" correspond with the next command, which advances the slideshow to the next slide.
     * keys: page up, up, left, and "-" correspond with the previous command, which advances the slideshow to the previous slide.
     * keys: "q" or "Q" correspond with the exit command, which shuts down the application.
     * @param keyEvent an instance of {@code java.awt.event.KeyEvent}
     */
    @Override
    public void keyPressed(KeyEvent keyEvent){
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_RIGHT:
            case '+':
                notifyObservers(new NextCommand(services));
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case '-':
                notifyObservers(new PreviousCommand(services));
                break;
            case 'q':
            case 'Q':
                notifyObservers(new ExitCommand());
                break;
            default:
                break;
        }
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
