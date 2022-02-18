package Exception;

/**
 * the InputMismatchException is an exception class
 * the class is used when a wrong input type was introduced
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
public class InputMismatchException extends Exception {
    public String errorMessage;
    public InputMismatchException(String errorMessage)
    {

        this.errorMessage=errorMessage;
    }
    @Override
    public String getMessage(){
        return errorMessage;
    }

}
