package mk.finki.ukim.wp.eparking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ParkingPlace {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String city;

    private int pricePerHour;

    private String description;

    public ParkingPlace () {}

    @ManyToOne
    private Person person;

    public ParkingPlace(String name, String city, int pricePerHour, String description) {
        this.name = name;
        this.city = city;
        this.pricePerHour = pricePerHour;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public Person getPerson() {
        return person;
    }
}
