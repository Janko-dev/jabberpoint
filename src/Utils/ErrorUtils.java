package Utils;

/**
 * Error utilities containing static methods.
 */
public class ErrorUtils {

    /**
     * Asserts equality of a boolean expression at runtime.
     * If assertion fails, a new RunTimeException is thrown that contains a provided message
     * @param eq equality that will be tested.
     * @param msg String message to display, when assertion fails.
     * @exception RuntimeException thrown when assertion fails.
     */
    public static void assertEquals(boolean eq, String msg){
        if (!eq) {
            throw new RuntimeException(msg);
        }
    }
}
