package mk.finki.ukim.wp.eparking.service;

import mk.finki.ukim.wp.eparking.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Optional<Reservation> makeReservation(LocalDate reservationForDate, LocalTime startTime, LocalTime endTime, Long parkingPlaceId, Long vehicleId);

    Optional<Reservation> editReservation(Long id, LocalDate reservationForDate, LocalTime startTime, LocalTime endTime); //List<Long> personIds, Long parkingPlaceId, Long vehicleId

    Optional<Reservation> approveReservation(Long id, boolean isReserved);

    Optional<Reservation> payReservation(Long id, boolean isPayed);

    void deleteById(Long id);
}
