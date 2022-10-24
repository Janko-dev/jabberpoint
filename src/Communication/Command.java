package Communication;

/**
 * Interface that allows for the use of the command pattern,
 * which delegates execution of code to a further specified time,
 * due to the combination with the observer pattern.
 */
public interface Command {
    public void execute();
}
