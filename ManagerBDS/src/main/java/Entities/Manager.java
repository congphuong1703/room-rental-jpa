package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "managers")
public class Manager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "username", unique = true)
    protected String username;
    @Column(name = "phone_number")
    protected String phoneNumber;
    @Column
    protected String email;
    @Column
    protected String mobile;
    @Column
    protected String firstName;
    @Column
    protected String lastName;
    @Column
    protected String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "manager")
    protected Collection<Persisted> persistedCollection;

    public Manager() {
    }

    public Manager(long id, String username, String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Manager(String username, String phoneNumber, String email, String password, String firstName, String lastName, String mobile) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }


    public Manager(String username, String phoneNumber, String email, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Persisted> getPersistedCollection() {
        return persistedCollection;
    }

    public void setPersistedCollection(Collection<Persisted> persistedCollection) {
        this.persistedCollection = persistedCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name   : " + this.getUsername() + "\n" +
                "Email  : " + this.getEmail() + "\n" +
                "Phone  : " + this.getPhoneNumber();
    }
}