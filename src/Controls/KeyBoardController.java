package Controls;

import Communication.Observable;
import Communication.Observer;

import java.util.ArrayList;

public class KeyBoardController implements Observable<Direction> {

    private ArrayList<Observer<Direction>> observers;

    public KeyBoardController(){
        observers = new ArrayList<>();
    }

    @Override
    public void add(Observer<Direction> observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer<Direction> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Direction direction) {
        for (Observer<Direction> ob : observers){
            ob.update(direction);
        }
    }
}
