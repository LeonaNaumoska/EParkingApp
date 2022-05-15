package mk.finki.ukim.wp.eparking.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingPlaceNotFoundException extends RuntimeException{

    public ParkingPlaceNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }

}
