package mk.finki.ukim.wp.eparking.service;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Rating;

import java.util.Optional;

public interface RatingService {

    Optional<Rating> findById(Long id);

    Optional<Rating> ratingForParking(float grade, Long parkingPlaceId); //add == save

    Optional<Rating> editRatingForParking(Long id, float grade, Long parkingPlaceId); //edit function

    void deleteById(Long id);
}
