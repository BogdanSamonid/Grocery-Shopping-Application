package exceptions;

public class WrongUsernameException extends Exception {

    public WrongUsernameException() {

        super("This username is not registered. Try again!");

    }
}

