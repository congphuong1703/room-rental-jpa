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

    public PersistedForInRent(String tenantName, String timerRent, Long id, String address, String description, int numberOfBed, double price, Manager manager) {
        super(id, address, description, numberOfBed, price, manager);
        this.timerRent = timerRent;
        this.tenantName = tenantName;
    }

    public PersistedForInRent() {
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

    @Override
    public String toString() {
        return super.toString() +
                "Rental Price    : $" + this.getPrice() + " per week\n"+
                "Tenant Name   : " + this.getTenantName();
    }
}
