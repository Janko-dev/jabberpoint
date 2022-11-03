package Communication;

/**
 * Interface that allows clients to be an observer of publishers.
 */
public interface Observer {

    public void update(Command command);
}
