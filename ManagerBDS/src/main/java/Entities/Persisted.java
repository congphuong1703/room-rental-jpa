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
    protected String propertyType;
    @Column
    protected int numberOfBathRooms;
    @Column
    protected String city;
    @Column
    protected String state;
    @Column
    protected int postCode;
    @Column
    protected String streetName;
    @Column
    protected String streetNumber;
    @Column
    protected String country;
    @Column
    protected String description;
    @Column(name = "number_of_bed")
    protected int numberOfBed;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    protected Manager manager;

    public Persisted() {
    }

    public Persisted(Long id, String description, int numberOfBed,  Manager manager) {
        this.id = id;
        this.description = description;
        this.numberOfBed = numberOfBed;
        this.manager = manager;
    }

    public Persisted(Long id, String propertyType, int numberOfBathRooms, String city,
                     String state, int postCode, String streetName, String streetNumber, String country, String description,
                     int numberOfBed,  Manager manager) {
        this.id = id;
        this.propertyType = propertyType;
        this.numberOfBathRooms = numberOfBathRooms;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.country = country;
        this.description = description;
        this.numberOfBed = numberOfBed;
        this.manager = manager;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumberOfBathRooms() {
        return numberOfBathRooms;
    }

    public void setNumberOfBathRooms(int numberOfBathRooms) {
        this.numberOfBathRooms = numberOfBathRooms;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
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

    public void add(Scanner sc) {
      /*  System.out.println("Enter address : ");
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
        }*/
    }

}
