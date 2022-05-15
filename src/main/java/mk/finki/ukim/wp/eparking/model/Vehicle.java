package mk.finki.ukim.wp.eparking.model;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private long id;

    private String brandName;

    private String registration; //registration of the Vehicle

    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle () {}

    @ManyToOne
    private Person person;

    public Vehicle(String brandName, String registration) { //, VehicleType vehicleType
        this.brandName = brandName;
        this.registration = registration;
       // this.vehicleType = vehicleType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getRegistration() {
        return registration;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Person getPerson() {
        return person;
    }
}
