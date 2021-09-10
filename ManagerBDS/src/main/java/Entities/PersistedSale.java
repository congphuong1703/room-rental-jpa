package Entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("sale")
@Table(name = "persisted_sale")
public class PersistedSale extends Persisted {

    @Column
    private double area;

    public PersistedSale(double area) {
        this.area = area;
    }

    public PersistedSale() {
    }

    public PersistedSale(Long id, String address, String description, int numberOfBed, double price, Manager manager, double area) {
        super(id, address, description, numberOfBed, price, manager);
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Sale Price    : $" + this.getPrice() + "\n"+
                "Area          : " + this.area + "m2";
    }
}
