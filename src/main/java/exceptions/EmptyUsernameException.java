package exceptions;

public class EmptyUsernameException extends Exception {

    private String username;

    public EmptyUsernameException(String username) {

        super(String.format("The username field is mandatory!", username));

    }
}