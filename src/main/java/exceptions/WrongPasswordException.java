package exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {

        super(String.format("Password not correct. Try again!"));

    }
}

