package exceptions;

public class EmptyPasswordException extends Exception{

    String password;

    public EmptyPasswordException(String password){

        super("The password field is mandatory!");
        this.password=password;
    }
}

