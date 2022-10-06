package Communication;

public class ExitCommand implements Command{
    @Override
    public void execute() {
        // code for shutting down jabberpoint system
        System.exit(0);
    }
}
