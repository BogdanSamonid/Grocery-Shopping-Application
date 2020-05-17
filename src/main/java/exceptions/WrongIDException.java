package exceptions;

public class WrongIDException extends Exception {

    public WrongIDException() {

        super("The ID is incorrect. Try again!");

    }
}
