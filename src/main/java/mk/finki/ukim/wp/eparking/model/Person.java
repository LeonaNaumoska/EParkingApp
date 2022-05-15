package mk.finki.ukim.wp.eparking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email; //this is actually the username of the user

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Person(){ }

    @OneToOne
    private Rating rating;

    public Person(String name, String surname, String phoneNumber, String email, String password, Role userRole) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
