package Entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("sale")
@Table(name = "persisted_sale")
public class PersistedSale extends Persisted {

    @Column
    private double salePrice;

    public PersistedSale(double salePrice) {
        this.salePrice = salePrice;
    }

    public PersistedSale() {
    }

    public PersistedSale(Persisted persisted) {
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

    public PersistedSale(Persisted persisted,double salePrice) {
        this.description = persisted.getDescription();
        this.numberOfBed = persisted.getNumberOfBed();
        this.propertyType = persisted.getPropertyType();
        this.manager = persisted.getManager();
        this.salePrice = salePrice;
        this.state = persisted.getState();
        this.postCode = persisted.getPostCode();
        this.city = persisted.getCity();
        this.numberOfBathRooms = persisted.getNumberOfBathRooms();
        this.streetName = persisted.getStreetName();
        this.country = persisted.getCountry();
        this.streetNumber = persisted.getStreetNumber();
    }


    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

}
