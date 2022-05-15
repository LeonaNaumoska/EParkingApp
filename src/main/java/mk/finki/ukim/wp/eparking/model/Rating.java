package mk.finki.ukim.wp.eparking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private long id;

    private float grade;

    public Rating () {}

    @ManyToOne
    private ParkingPlace parkingPlace;

    public Rating(float grade, ParkingPlace parkingPlace) {
        this.grade = grade;
        this.parkingPlace = parkingPlace;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
