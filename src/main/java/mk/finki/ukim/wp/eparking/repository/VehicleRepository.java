package mk.finki.ukim.wp.eparking.repository;

import mk.finki.ukim.wp.eparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Optional<Vehicle> findByBrandName(String brandName);
}
