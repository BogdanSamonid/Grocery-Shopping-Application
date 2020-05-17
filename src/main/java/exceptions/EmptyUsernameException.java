package exceptions;

public class EmptyUsernameException extends Exception {

    String username;

    public EmptyUsernameException(String username) {

        super("The username field is mandatory!");
        this.username=username;
    }
}