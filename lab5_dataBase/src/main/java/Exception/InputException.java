package Exception;

/**
 * the InputException is an exception class
 * the class is used when an invalid input was introduced
 *
 * @author Bohm Evelin
 * @version  07.11.2021
 * @since 1.0
 */
public class InputException extends Exception {
    public InputException(String s) {
        System.out.println(s);
    }
}
