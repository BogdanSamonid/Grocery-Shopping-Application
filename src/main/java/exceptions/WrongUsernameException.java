package exceptions;

public class WrongUsernameException extends Exception {

    public WrongUsernameException() {

        super(String.format("This username does not exist. Try again!"));

    }
}

