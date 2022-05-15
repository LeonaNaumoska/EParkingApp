package mk.finki.ukim.wp.eparking.service;

import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Role;

import java.util.List;

public interface PersonService {

    Person register(String name, String surname, String phoneNumber, String email, String password, String repeatPassword, Role userRole);

    Person login(String email, String password);

    List<Person> findAll();

    //Todo: givesRating
}
