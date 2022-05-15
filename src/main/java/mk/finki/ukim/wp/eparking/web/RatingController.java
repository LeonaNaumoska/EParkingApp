package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.ParkingPlace;
import mk.finki.ukim.wp.eparking.model.Rating;
import mk.finki.ukim.wp.eparking.service.ParkingPlaceService;
import mk.finki.ukim.wp.eparking.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final ParkingPlaceService parkingPlaceService;

    public RatingController(RatingService ratingService, ParkingPlaceService parkingPlaceService) {
        this.ratingService = ratingService;
        this.parkingPlaceService = parkingPlaceService;
    }

    @GetMapping
    public String getRatingPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<ParkingPlace> parkingPlaces = this.parkingPlaceService.findAll(); //TODO
        model.addAttribute("parkingPlaces", parkingPlaces);
        return "ratings";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRatingPlace(@PathVariable Long id) {
        this.ratingService.deleteById(id);
        return "redirect:/ratings";
    }

    @GetMapping("/edit-form/{id}")
    public String editRatingPage(@PathVariable Long id, Model model) {
        if (this.ratingService.findById(id).isPresent()) {
            Rating rating = this.ratingService.findById(id).get();
            List<ParkingPlace> parkingPlaces = this.parkingPlaceService.findAll();
            model.addAttribute("parkingPlaces", parkingPlaces);
            model.addAttribute("rating", rating);
            return "ratings";
        }
        return "redirect:/ratings?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addRating(Model model) {
        List<ParkingPlace> parkingPlaces = this.parkingPlaceService.findAll();
        model.addAttribute("parkingPlaces", parkingPlaces);
        return "ratings";
    }

    @PostMapping("/add")
    public String saveRating(
            @RequestParam(required = false) Long id,
            @RequestParam float grade,
            @RequestParam Long parkingPlace) {
        if (id != null) {
            this.ratingService.editRatingForParking(id, grade, parkingPlace);
        } else {
            this.ratingService.ratingForParking(grade, parkingPlace);
        }
        return "redirect:/ratings";
    }
}
