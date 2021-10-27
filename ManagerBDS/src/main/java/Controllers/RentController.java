package Controllers;

import Entities.*;
import Services.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "rentController", eager = true)
@RequestScoped
public class RentController {

    private Persisted persisted = new Persisted();
    private PersistedRent rent = new PersistedRent();

    private List<PersistedRent> rents = new ArrayList<>();
    private ManagerService managerService = new ManagerServiceImp();
    private BaseService rentService = new PersistedRentServiceImp();
    private PersistedService persistedService = new PersistedServiceImp();

    public String listRents() {
        rents = rentService.getAll();
        return "views/rent/listRents";
    }

    public String addNewRent() {
        rent = new PersistedRent(persisted);
        rentService.save(rent);
        return "views/manager/listManagers";
    }

    public String searchRent(Long id) throws IOException {
        Persisted persisted = persistedService.getById(id);
        if (persisted != null && persisted instanceof PersistedSale) {
            PersistedRent persistedRent = (PersistedRent) persisted;
            rents = new ArrayList<PersistedRent>() {{
                add(persistedRent);
            }};
        } else {
            showMessage("Not found rent property by id : " + id);
        }
        return "views/rent/listRents";
    }

    private void showMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary));
    }

    public Persisted getPersisted() {
        return persisted;
    }

    public void setPersisted(Persisted persisted) {
        this.persisted = persisted;
    }

    public PersistedRent getRent() {
        return rent;
    }

    public void setRent(PersistedRent rent) {
        this.rent = rent;
    }

    public List<PersistedRent> getRents() {
        return rents;
    }

    public void setRents(List<PersistedRent> rents) {
        this.rents = rents;
    }
}
