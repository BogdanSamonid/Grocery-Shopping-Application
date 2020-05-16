package exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {

        super(String.format("The password is not correct. Try again!"));

    }
}

