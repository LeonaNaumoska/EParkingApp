package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Vehicle;
import mk.finki.ukim.wp.eparking.model.VehicleType;
import mk.finki.ukim.wp.eparking.service.PersonService;
import mk.finki.ukim.wp.eparking.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final PersonService personService;
    private final VehicleService vehicleService;

    public VehicleController(PersonService personService, VehicleService vehicleService) {
        this.personService = personService;
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String getVehiclePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Vehicle> vehicles = this.vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        this.vehicleService.deleteById(id);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit-form/{id}")
    public String editVehiclePage(@PathVariable Long id, Model model) {
        if (this.vehicleService.findById(id).isPresent()) {
            Vehicle vehicle = this.vehicleService.findById(id).get();
            List<Person> personList = this.personService.findAll();
            model.addAttribute("vehicle", vehicle);
            return "vehicles";
        }
        return "redirect:/vehicles?error=ProductNotFound";
    }


    @GetMapping("/add")
    public String addVehiclePage(Model model) {
        List<Person> personList = this.personService.findAll();
        model.addAttribute("personList", personList);
        return "add-vehicle";
    }

    @PostMapping("/add-vehicle")
    public String saveVehicle(
            @RequestParam(required = false) String id,
            @RequestParam String name,
            @RequestParam String registration
            ) {
        if (!id.equals("")) {
            Long vehicleId = Long.parseLong(id);
            this.vehicleService.edit(vehicleId, name, registration);
        } else {
            this.vehicleService.save(name, registration);
        }
        return "redirect:/vehicles";
    }

}
