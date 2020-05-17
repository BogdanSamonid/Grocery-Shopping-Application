package exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {

        super("The password is not correct. Try again!");

    }
}

