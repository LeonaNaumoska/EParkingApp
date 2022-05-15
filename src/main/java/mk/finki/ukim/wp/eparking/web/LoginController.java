package mk.finki.ukim.wp.eparking.web;

import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.eparking.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final PersonService personService;

    public LoginController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {

        Person person = null;

        try{
            person = this.personService.login(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("email", person);
            return "redirect:/home";
        } catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
