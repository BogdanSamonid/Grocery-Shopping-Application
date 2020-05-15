package exceptions;

public class EmptyIDException extends Exception{

    private String ID;

    public EmptyIDException(String ID){

        super(String.format("The ID field is mandatory!", ID));

    }
}


