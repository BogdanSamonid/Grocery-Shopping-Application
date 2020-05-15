package exceptions;

public class WrongIDException extends Exception {

    public WrongIDException() {

        super(String.format("The ID is incorrect. Try again!"));

    }
}
