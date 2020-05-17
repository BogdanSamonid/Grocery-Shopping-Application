package exceptions;

public class EmptyIDException extends Exception{

    String ID;

    public EmptyIDException(String ID){

        super("The ID field is mandatory!");
        this.ID=ID;
    }
}


