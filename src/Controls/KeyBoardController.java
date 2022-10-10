package Controls;

import Communication.*;
import Domain.Services.Facade.DomainServicesFacade;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoardController extends KeyAdapter implements Observable {

    private ArrayList<Observer> observers;
    private DomainServicesFacade services;

    public KeyBoardController(DomainServicesFacade services){
        observers = new ArrayList<>();
        this.services = services;
    }

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
