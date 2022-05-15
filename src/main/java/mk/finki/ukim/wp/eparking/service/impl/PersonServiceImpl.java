package mk.finki.ukim.wp.eparking.service.impl;

import mk.finki.ukim.wp.eparking.model.Person;
import mk.finki.ukim.wp.eparking.model.Role;
import mk.finki.ukim.wp.eparking.model.exceptions.InvalidEmailOrPasswordException;
import mk.finki.ukim.wp.eparking.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.eparking.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.eparking.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.eparking.repository.PersonRepository;
import mk.finki.ukim.wp.eparking.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person register(String name, String surname, String phoneNumber, String email, String password, String repeatPassword, Role userRole) {

        if(email==null || email.isEmpty()  || password==null || password.isEmpty()){
            throw new InvalidEmailOrPasswordException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if(this.personRepository.findByEmail(email).isPresent()){
            throw new UsernameAlreadyExistsException(email);
        }

        Person person = new Person(name, surname, phoneNumber, email, password, userRole);

        return personRepository.save(person);
    }

    @Override
    public Person login(String email, String password) {

        if(email==null || email.isEmpty()  || password==null || password.isEmpty()){
            throw new InvalidEmailOrPasswordException();
        }

        return personRepository.findByEmailAndPassword(email, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }
}
