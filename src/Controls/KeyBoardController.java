package Controls;

import Communication.*;
import UI.Projector;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoardController extends KeyAdapter implements Observable {

    private ArrayList<Observer> observers;
    private Projector projector;

    public KeyBoardController(Projector projector){
        observers = new ArrayList<>();
        this.projector = projector;
    }

    public void keyPressed(KeyEvent keyEvent){
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                notifyObservers(new NextCommand(projector));
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                notifyObservers(new PreviousCommand(projector));
                break;
            case 'q':
            case 'Q':
                System.exit(0);
                break;
            default:
                break;

        }
    }


    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Command command) {
        for (Observer ob : observers){
            ob.update(command);
        }
    }
}
