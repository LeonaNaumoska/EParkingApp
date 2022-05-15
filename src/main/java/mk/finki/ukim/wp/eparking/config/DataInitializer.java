package mk.finki.ukim.wp.eparking.config;

import mk.finki.ukim.wp.eparking.service.PersonService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final PersonService personService;

    public DataInitializer(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void initData(){
        System.out.println("Hello world!");
    }
}
