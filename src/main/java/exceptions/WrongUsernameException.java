package exceptions;

public class WrongUsernameException extends Exception {

    public WrongUsernameException() {

        super(String.format("This username is not registered. Try again!"));

    }
}

