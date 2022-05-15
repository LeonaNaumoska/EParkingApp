package mk.finki.ukim.wp.eparking.repository;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    Optional<ParkingPlace> findByName(String name);

    Optional<ParkingPlace> findByCity(String city);

    //Optional<ParkingPlace> findByNameAnAndCity(String name, String city);

}
