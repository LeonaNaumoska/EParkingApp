package mk.finki.ukim.wp.eparking.service.impl;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Reservation;
import mk.finki.ukim.wp.eparking.model.Vehicle;
import mk.finki.ukim.wp.eparking.model.exceptions.ParkingPlaceNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.PersonNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.ReservationPlaceNotFoundException;
import mk.finki.ukim.wp.eparking.model.exceptions.VehicleNotFoundException;
import mk.finki.ukim.wp.eparking.repository.ParkingPlaceRepository;
import mk.finki.ukim.wp.eparking.repository.PersonRepository;
import mk.finki.ukim.wp.eparking.repository.ReservationRepository;
import mk.finki.ukim.wp.eparking.repository.VehicleRepository;
import mk.finki.ukim.wp.eparking.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final PersonRepository personRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, PersonRepository personRepository, ParkingPlaceRepository parkingPlaceRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.personRepository = personRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return this.reservationRepository.findById(id);
    }

    @Override
    public Optional<Reservation> makeReservation(LocalDate reservationForDate, LocalTime startTime, LocalTime endTime, Long parkingPlaceId, Long vehicleId) {
//        List<Person> personList = this.personRepository.findAllById(personIds);
        ParkingPlace parkingPlace = this.parkingPlaceRepository.findById(parkingPlaceId).orElseThrow(() -> new ParkingPlaceNotFoundException(parkingPlaceId));
        Vehicle vehicle = this.vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        Reservation reservation = new Reservation(reservationForDate, startTime, endTime, parkingPlace, vehicle);

        return Optional.of(this.reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> editReservation(Long id, LocalDate reservationForDate, LocalTime startTime, LocalTime endTime) {
//        List<Person> personList = this.personRepository.findAllById(personIds);
//        ParkingPlace parkingPlace = this.parkingPlaceRepository.findById(parkingPlaceId).orElseThrow(() -> new ParkingPlaceNotFoundException(parkingPlaceId));
//        Vehicle vehicle = this.vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        Reservation reservation = this.reservationRepository.findById(id).orElseThrow(() -> new ReservationPlaceNotFoundException(id));

        reservation.setReservationForDate(reservationForDate);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
//        reservation.setVehicle(vehicle);
//        reservation.setParkingPlace(parkingPlace);

        return Optional.of(this.reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> approveReservation(Long id, boolean isReserved) {

        Reservation reservation = this.reservationRepository.findById(id).orElseThrow(() -> new ReservationPlaceNotFoundException(id));

        reservation.setReserved(isReserved);

        return Optional.of(this.reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> payReservation(Long id, boolean isPayed) {

        Reservation reservation = this.reservationRepository.findById(id).orElseThrow(() -> new ReservationPlaceNotFoundException(id));

        reservation.setPayed(isPayed);

        return Optional.of(this.reservationRepository.save(reservation));
    }

    @Override
    public void deleteById(Long id) {
        this.reservationRepository.deleteById(id);
    }
}
