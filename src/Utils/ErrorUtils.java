package Utils;

public class ErrorUtils {

    public static void assertEquals(boolean eq, String msg){
        if (!eq) {
            throw new RuntimeException(msg);
        }
    }
}
