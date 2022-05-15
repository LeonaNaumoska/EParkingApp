package mk.finki.ukim.wp.eparking.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username) {
        super(String.format("User with username or email: %s already exists", username));
    }

}
