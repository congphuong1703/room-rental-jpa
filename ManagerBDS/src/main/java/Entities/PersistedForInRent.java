package Entities;

import javax.persistence.*;

@Entity
@Table(name = "persisted_for_in_rent")
@DiscriminatorColumn(name = "in_rent")
public class PersistedForInRent extends Persisted {

    @Column(name = "timer_rent")
    private String timerRent;

    @Column(name = "tenant_name")
    private String tenantName;

    public PersistedForInRent() {
    }

    public PersistedForInRent(Persisted persisted) {
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

    public PersistedForInRent(Persisted persisted, String tenantName) {
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
        this.tenantName = tenantName;
    }

    public PersistedForInRent(String timerRent, String tenantName) {
        this.timerRent = timerRent;
        this.tenantName = tenantName;
    }

    public String getTimerRent() {
        return timerRent;
    }

    public void setTimerRent(String timerRent) {
        this.timerRent = timerRent;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
