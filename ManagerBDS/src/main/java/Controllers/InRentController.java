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

@ManagedBean(name = "inRentController", eager = true)
@RequestScoped
public class InRentController {

    private Persisted persisted = new Persisted();
    private PersistedForInRent persistedForInRent = new PersistedForInRent();

    private List<PersistedForInRent> persistedForInRents = new ArrayList<>();
    private ManagerService managerService = new ManagerServiceImp();
    private BaseService inRentService = new PersistedForInRentServiceImp(managerService);
    private PersistedService persistedService = new PersistedServiceImp();

    public String listInRents() {
        persistedForInRents = inRentService.getAll();
        return "views/intrent/listInRents";
    }

    public String addNewInRent() {

        Persisted persistedInStorage = persistedService.getById(persisted.getId());
        if (persistedInStorage != null && persistedInStorage instanceof PersistedSale) {
            persistedForInRent = new PersistedForInRent(persistedInStorage);
            inRentService.save(persistedForInRent);
        } else {
            showMessage("Not found property by id : " + persisted.getId());
        }

        return "views/inrent/listInRents";
    }


    public String searchInRent(Long id) {
        //query get property by id
        Persisted persisted = persistedService.getById(id);
        //check null && type is persistedsale
        if (persisted != null && persisted instanceof PersistedForInRent) {
            //cast to persisted from persistedsale
            PersistedForInRent inRent = (PersistedForInRent) persisted;
            //constructor new array with element is new sale property
            persistedForInRents = new ArrayList<PersistedForInRent>() {{
                add(inRent);
            }};
        } else {
            showMessage("Not found InRent property by id : " + id);
        }
        //return view
        return "views/inrent/listInRents";
    }

    public Persisted getPersisted() {
        return persisted;
    }

    public void setPersisted(Persisted persisted) {
        this.persisted = persisted;
    }

    public PersistedForInRent getPersistedForInRent() {
        return persistedForInRent;
    }

    public void setPersistedForInRent(PersistedForInRent persistedForInRent) {
        this.persistedForInRent = persistedForInRent;
    }

    public List<PersistedForInRent> getPersistedForInRents() {
        return persistedForInRents;
    }

    public void setPersistedForInRents(List<PersistedForInRent> persistedForInRents) {
        this.persistedForInRents = persistedForInRents;
    }

    private void showMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary));
    }


}
