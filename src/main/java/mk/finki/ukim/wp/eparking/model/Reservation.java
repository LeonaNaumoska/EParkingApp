package mk.finki.ukim.wp.eparking.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private long id;

    private LocalDate reservationForDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isReserved = false;

    private boolean isPayed = false;

    public Reservation () {}

    @ManyToMany
    private List<Person> personList;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private ParkingPlace parkingPlace;

    public Reservation(LocalDate reservationForDate, LocalTime startTime, LocalTime endTime, ParkingPlace parkingPlace, Vehicle vehicle) {
        this.reservationForDate = reservationForDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingPlace = parkingPlace;
        this.vehicle = vehicle;
    }

    public void setReservationForDate(LocalDate reservationForDate) {
        this.reservationForDate = reservationForDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public long getId() {
        return id;
    }

    public LocalDate getReservationForDate() {
        return reservationForDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }
}
