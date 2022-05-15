package mk.finki.ukim.wp.eparking.service.impl;

import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Vehicle;
import mk.finki.ukim.wp.eparking.model.VehicleType;
import mk.finki.ukim.wp.eparking.model.exceptions.PersonNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.VehicleNotFoundException;
import mk.finki.ukim.wp.eparking.repository.PersonRepository;
import mk.finki.ukim.wp.eparking.repository.VehicleRepository;
import mk.finki.ukim.wp.eparking.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final PersonRepository personRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, PersonRepository personRepository) {
        this.vehicleRepository = vehicleRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return this.vehicleRepository.findById(id);
    }

    @Override
    public Optional<Vehicle> findByBrandName(String brandName) {
        return this.vehicleRepository.findByBrandName(brandName);
    }

    @Override
    public Optional<Vehicle> save(String brandName, String registration) {

        Vehicle vehicle = new Vehicle(brandName, registration);

        return Optional.of(this.vehicleRepository.save(vehicle));
    }

    @Override
    public Optional<Vehicle> edit(Long id, String brandName, String registration) {

        Vehicle vehicle = this.vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));

        vehicle.setBrandName(brandName);
        vehicle.setRegistration(registration);
        //vehicle.setVehicleType(vehicleType);

        return Optional.of(this.vehicleRepository.save(vehicle));
    }

    @Override
    public void deleteById(Long id) {
        this.vehicleRepository.deleteById(id);
    }
}
