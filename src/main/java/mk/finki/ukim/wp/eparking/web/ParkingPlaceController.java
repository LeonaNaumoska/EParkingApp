package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.service.ParkingPlaceService;
import mk.finki.ukim.wp.eparking.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parkingPlaces")
public class ParkingPlaceController {

    private final PersonService personService;
    private final ParkingPlaceService parkingPlaceService;

    public ParkingPlaceController(PersonService personService, ParkingPlaceService parkingPlaceService) {
        this.personService = personService;
        this.parkingPlaceService = parkingPlaceService;
    }

    @GetMapping
    public String getParkingPlacePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<ParkingPlace> parkingPlaces = this.parkingPlaceService.findAll();
        model.addAttribute("parkingPlaces", parkingPlaces);
        return "parkingPlaces";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteParkingPlace(@PathVariable Long id) {
        this.parkingPlaceService.deleteById(id);
        return "redirect:/parkingPlaces";
    }

    @GetMapping("/edit-form/{id}")
    public String editParkingPlace(@PathVariable Long id, Model model) {
        if (this.parkingPlaceService.findById(id).isPresent()) {
            ParkingPlace parkingPlace = this.parkingPlaceService.findById(id).get();
            List<Person> personList = this.personService.findAll();
            model.addAttribute("personList", personList);
            model.addAttribute("parkingPlace", parkingPlace);
            return "parkingPlaces";
        }
        return "redirect:/parkingPlaces?error=ProductNotFound";
    }

    @GetMapping("/add")
    public String addParkingPlace(Model model) {
        List<Person> personList = this.personService.findAll();
        model.addAttribute("personList", personList);
        return "add-parkingPlace";
    }

    @PostMapping("/add")
    public String saveParkingPlace( //adding a new parking place
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam int pricePerHour,
            @RequestParam String description
            ) {
        if (id != null) {
            this.parkingPlaceService.edit(id, name, city, pricePerHour, description);
        } else {
            this.parkingPlaceService.save(name, city, pricePerHour, description);
        }
        return "redirect:/parkingPlaces";
    }
}
