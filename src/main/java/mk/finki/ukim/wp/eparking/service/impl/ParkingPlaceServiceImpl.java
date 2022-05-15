package mk.finki.ukim.wp.eparking.service.impl;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.exceptions.ParkingPlaceNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.PersonNotFoundException;
import mk.finki.ukim.wp.eparking.repository.ParkingPlaceRepository;
import mk.finki.ukim.wp.eparking.repository.PersonRepository;
import mk.finki.ukim.wp.eparking.service.ParkingPlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    private final ParkingPlaceRepository parkingPlaceRepository;
    private final PersonRepository personRepository;

    public ParkingPlaceServiceImpl(ParkingPlaceRepository parkingPlaceRepository, PersonRepository personRepository) {
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<ParkingPlace> findAll() {
        return this.parkingPlaceRepository.findAll();
    }

    @Override
    public Optional<ParkingPlace> findById(Long id) {
        return this.parkingPlaceRepository.findById(id);
    }

    @Override
    public Optional<ParkingPlace> findByName(String name) {
        return this.parkingPlaceRepository.findByName(name);
    }

    @Override
    public Optional<ParkingPlace> findByCity(String city) {
        return this.parkingPlaceRepository.findByCity(city);
    }

    @Override
    public Optional<ParkingPlace> save(String name, String city, int pricePerHour, String description) { //, Long personId

        //Person person = this.personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));

        ParkingPlace parkingPlace = new ParkingPlace(name, city, pricePerHour, description);

        return Optional.of(this.parkingPlaceRepository.save(parkingPlace));
    }

    @Override
    public Optional<ParkingPlace> edit(Long id, String name, String city, int pricePerHour, String description) {

        ParkingPlace parkingPlace = this.parkingPlaceRepository.findById(id).orElseThrow(() -> new ParkingPlaceNotFoundException(id));
        //Person person = this.personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));

        parkingPlace.setName(name);
        parkingPlace.setCity(city);
        parkingPlace.setPricePerHour(pricePerHour);
        parkingPlace.setDescription(description);
        //parkingPlace.setPerson(person);

        return Optional.of(this.parkingPlaceRepository.save(parkingPlace));
    }

    @Override
    public void deleteById(Long id) {
        this.parkingPlaceRepository.deleteById(id);
    }
}
