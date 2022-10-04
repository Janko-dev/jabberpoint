package Controls;

import Communication.*;
import UI.Projector;
import UI.SlideShowProjector;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoardController extends KeyAdapter implements Observable {

    private ArrayList<Observer> observers;
    private SlideShowProjector projector;

    public KeyBoardController(SlideShowProjector projector){
        observers = new ArrayList<>();
        this.projector = projector;
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
                notifyObservers(new NextCommand(projector));
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
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
