package in.cubestack.supaldubey;

public class Assertions {

    public static void requireNotNull(Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }

    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }
}
