package Communication;

/**
 * Interface that allows clients to be a publishers for whomever is interested in observing
 */
public interface Publisher {

    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(Command command);
}
