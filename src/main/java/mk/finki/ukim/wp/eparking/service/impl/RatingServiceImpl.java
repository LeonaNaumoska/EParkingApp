package mk.finki.ukim.wp.eparking.service.impl;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Rating;
import mk.finki.ukim.wp.eparking.model.exceptions.ParkingPlaceNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.RatingNotFoundException;
import mk.finki.ukim.wp.eparking.repository.ParkingPlaceRepository;
import mk.finki.ukim.wp.eparking.repository.RatingRepository;
import mk.finki.ukim.wp.eparking.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, ParkingPlaceRepository parkingPlaceRepository) {
        this.ratingRepository = ratingRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return this.ratingRepository.findById(id);
    }

    @Override
    public Optional<Rating> ratingForParking(float grade, Long parkingPlaceId) {

        ParkingPlace parkingPlace = this.parkingPlaceRepository.findById(parkingPlaceId).orElseThrow(() -> new ParkingPlaceNotFoundException(parkingPlaceId));

        Rating rating = new Rating(grade, parkingPlace);

        return Optional.of(this.ratingRepository.save(rating));
    }

    @Override
    public Optional<Rating> editRatingForParking(Long id, float grade, Long parkingPlaceId) {

        ParkingPlace parkingPlace = this.parkingPlaceRepository.findById(parkingPlaceId).orElseThrow(() -> new ParkingPlaceNotFoundException(parkingPlaceId));

        Rating rating = this.ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException(id));

        rating.setGrade(grade);

        return Optional.of(this.ratingRepository.save(rating));
    }

    @Override
    public void deleteById(Long id) {
        this.ratingRepository.deleteById(id);
    }
}
