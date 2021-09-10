package Entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("rent")
@Table(name = "persisted_rent")
public class PersistedRent extends Persisted {

    @Column(name = "timer_rent")
    private String timerRent;


    public PersistedRent(String timerRent) {
        this.timerRent = timerRent;
    }

    public PersistedRent(Long id, String address, String description, int numberOfBed, double price, Manager manager, String timerRent) {
        super(id, address, description, numberOfBed, price, manager);
        this.timerRent = timerRent;
    }

    public PersistedRent() {
    }

    public String getTimerRent() {
        return timerRent;
    }

    public void setTimerRent(String timerRent) {
        this.timerRent = timerRent;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Rental Price    : $" + this.getPrice() + " per week\n"+
                "Timer rent   : " + this.getTimerRent();
    }
}
