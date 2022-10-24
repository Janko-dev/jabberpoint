package Communication;

/**
 * Interface that allows clients to be observable by observers
 */
public interface Observable {

    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(Command command);
}
