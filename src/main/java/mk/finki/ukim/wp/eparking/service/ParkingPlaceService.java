package mk.finki.ukim.wp.eparking.service;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;

import java.util.List;
import java.util.Optional;

public interface ParkingPlaceService {

    List<ParkingPlace> findAll();

    Optional<ParkingPlace> findById(Long id);

    Optional<ParkingPlace> findByName(String name);

    Optional<ParkingPlace> findByCity(String city);

    Optional<ParkingPlace> save(String name, String city, int pricePerHour, String description); //, Long personId

    Optional<ParkingPlace> edit(Long id, String name, String city, int pricePerHour, String description); //, Long personId

    void deleteById(Long id);
}
