package Entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("rent")
@Table(name = "persisted_rent")
public class PersistedRent extends Persisted {

    @Column
    private double rentCost;

    @Column
    private boolean isFurnished;

    public PersistedRent(Persisted persisted) {
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

    public PersistedRent(Persisted persisted, double rentCost, boolean isFurnished) {
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
        this.rentCost = rentCost;
        this.isFurnished = isFurnished;
    }

    public PersistedRent() {
    }


    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

}
