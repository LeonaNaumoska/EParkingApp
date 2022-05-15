package mk.finki.ukim.wp.eparking.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super("Invalid user credentials exception");
    }
}
