package Communication;

public interface Observable {

    public void add(Observer observer);
    public void remove(Observer observer);
    public void notifyObservers(Command command);
}
