package exceptions;

public class EmptyPasswordException extends Exception{

    private String password;

    public EmptyPasswordException(String password){

        super(String.format("The password field is mandatory!", password));

    }
}

