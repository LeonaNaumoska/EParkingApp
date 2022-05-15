package mk.finki.ukim.wp.eparking.repository;

import mk.finki.ukim.wp.eparking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);
    Optional<Person> findByEmailAndPassword(String email, String password);
}
