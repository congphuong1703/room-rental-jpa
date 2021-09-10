package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Scanner;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "persisted_type")
public class Persisted implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column
    protected String address;
    @Column
    protected String description;
    @Column(name = "number_of_bed")
    protected int numberOfBed;
    @Column
    protected double price;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Persisted() {
    }

    public Persisted(Long id, String address, String description, int numberOfBed, double price, Manager manager) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.numberOfBed = numberOfBed;
        this.price = price;
        this.manager = manager;
    }

    public Manager getUser() {
        return manager;
    }

    public void setUser(Manager manager) {
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void add(Scanner sc) {
        System.out.println("Enter address : ");
        this.address = sc.nextLine();
        System.out.println("Enter descriptions : ");
        this.description = sc.nextLine();
        boolean beakLoop = false;
        while (!beakLoop) {
            try {
                System.out.println("Enter number of bedrooms : ");
                this.numberOfBed = Integer.parseInt(sc.nextLine());
                System.out.println("Enter price :");
                this.price = Double.parseDouble(sc.nextLine());
                if (price < 0 || numberOfBed < 0) {
                    System.out.println("Price and number of bedrooms can't less than 0!");
                    throw new NumberFormatException();
                }
                beakLoop = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please input number type");
            }
        }
    }

    @Override
    public String toString() {
        return "Property ID        : " + this.getId() + "\n" +
                "Address            : " + this.getAddress() + "\n" +
                "Description        : " + this.getDescription() + "\n" +
                "Number of bedrooms : " + this.getNumberOfBed() + "\n";
    }
}
