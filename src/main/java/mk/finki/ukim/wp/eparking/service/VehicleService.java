package mk.finki.ukim.wp.eparking.service;

import mk.finki.ukim.wp.eparking.model.Vehicle;
import mk.finki.ukim.wp.eparking.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAll();

    Optional<Vehicle> findById(Long id);

    Optional<Vehicle> findByBrandName(String brandName);

    Optional<Vehicle> save(String brandName, String registration); //, VehicleType vehicleType

    Optional<Vehicle> edit(Long id, String brandName, String registration); //, VehicleType vehicleType

    void deleteById(Long id);
}
