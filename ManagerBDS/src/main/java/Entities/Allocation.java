package Entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "allocation")
@DiscriminatorColumn(name = "allocate_persist")
public class Allocation extends Persisted {

    @Column
    protected LocalDate timCreated;

    public Allocation(LocalDate timCreated) {
        this.timCreated = timCreated;
    }

    public Allocation() {
    }

    public Allocation(Persisted persisted) {
        this.description = persisted.getDescription();
        this.numberOfBed = persisted.getNumberOfBed();
        this.propertyType = persisted.getPropertyType();
        this.manager = persisted.getManager();
        this.state = persisted.getState();
        this.postCode = persisted.getPostCode();
        this.city = persisted.getCity();
        this.numberOfBathRooms = persisted.getNumberOfBathRooms();
        this.streetName = persisted.getStreetName();
        this.country = persisted.getCountry();
        this.streetNumber = persisted.getStreetNumber();
    }

    public Allocation(Persisted persisted, LocalDate timCreated) {
        this.description = persisted.getDescription();
        this.numberOfBed = persisted.getNumberOfBed();
        this.propertyType = persisted.getPropertyType();
        this.manager = persisted.getManager();
        this.state = persisted.getState();
        this.postCode = persisted.getPostCode();
        this.city = persisted.getCity();
        this.numberOfBathRooms = persisted.getNumberOfBathRooms();
        this.streetName = persisted.getStreetName();
        this.country = persisted.getCountry();
        this.streetNumber = persisted.getStreetNumber();
        this.timCreated = timCreated;
    }

    public LocalDate getTimCreated() {
        return timCreated;
    }

    public void setTimCreated(LocalDate timCreated) {
        this.timCreated = timCreated;
    }
}
