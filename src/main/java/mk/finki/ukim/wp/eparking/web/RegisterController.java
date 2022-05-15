package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.Role;
import mk.finki.ukim.wp.eparking.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.eparking.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.eparking.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final PersonService personService;

    public RegisterController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/profile")
    public String info(){
        return "profile";
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String phoneNumber,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam Role userRole){

        try {
            this.personService.register(name, surname, phoneNumber, email, password, repeatPassword, userRole);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
