package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Reservation;
import mk.finki.ukim.wp.eparking.model.Vehicle;
import mk.finki.ukim.wp.eparking.service.ParkingPlaceService;
import mk.finki.ukim.wp.eparking.service.PersonService;
import mk.finki.ukim.wp.eparking.service.ReservationService;
import mk.finki.ukim.wp.eparking.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final PersonService personService;
    private final VehicleService vehicleService;
    private final ParkingPlaceService parkingPlaceService;

    public ReservationController(ReservationService reservationService, PersonService personService, VehicleService vehicleService, ParkingPlaceService parkingPlaceService) {
        this.reservationService = reservationService;
        this.personService = personService;
        this.vehicleService = vehicleService;
        this.parkingPlaceService = parkingPlaceService;
    }

    @GetMapping
    public String getReservation(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Reservation> reservations = this.reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        this.reservationService.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/edit-form/{id}")
    public String editReservation(@PathVariable Long id, Model model) {
        if (this.reservationService.findById(id).isPresent()) {
            Reservation reservation = this.reservationService.findById(id).get();

            // TODO: ovde da se dopolni so relaciite koi sto gi ima rezervacija


            model.addAttribute("reservations", reservation);
            return "reservations";
        }
        return "redirect:/reservations?error=ProductNotFound";
    }

    //make reservation
    @GetMapping("/add/{id}")
    public String makeReservation(@PathVariable Long id, Model model) {
//        Optional<ParkingPlace> parkingPlace =this.parkingPlaceService.findById(id);
//        model.addAttribute("parkingPlace", parkingPlace);

        List<Vehicle> vehicles = this.vehicleService.findAll();
        List<Person> personList = this.personService.findAll();
        model.addAttribute("personList", personList);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("parkingId", id);
        return "make-reservation";
    }

    @PostMapping("/add")
    public String saveReservation(
            @RequestParam String date,
            @RequestParam String startTime,
            @RequestParam String entTime,
            @RequestParam String vehicle,
            @RequestParam String parkingId) {

        String [] parts = date.split("-");
        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        LocalDate newDate = LocalDate.of(year, month, day);

        Long vehicleId = Long.parseLong(vehicle);
        Long parkingPlaceId = Long.parseLong(parkingId);

        String [] startTimeParts = startTime.split(":");
        String [] endTimeParts = entTime.split(":");
        LocalTime newStartTime = LocalTime.of(Integer.parseInt(startTimeParts[0]), Integer.parseInt(startTimeParts[1]));
        LocalTime newEndTime = LocalTime.of(Integer.parseInt(endTimeParts[0]), Integer.parseInt(endTimeParts[1]));
        this.reservationService.makeReservation(newDate, newStartTime, newEndTime, parkingPlaceId, vehicleId);
        return "redirect:/reservations";
    }
}
