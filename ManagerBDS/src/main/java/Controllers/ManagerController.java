package Controllers;

import Entities.Manager;
import Services.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Khai báo been
@ManagedBean
@RequestScoped
public class ManagerController implements Serializable {
    private static final long serialVersionUID = 5443351151396868724L;

    public Manager manager;

    public List<Manager> managers = new ArrayList<>();
    public ManagerService managerService = new ManagerServiceImp();

    @PostConstruct
    public void init() {
        manager = new Manager();
        managers = managerService.getAll();
    }

    public String listManagers() {
        managers = managerService.getAll();
        //trả ra file listManager.xhtml
        return "views/manager/listManagers";
    }

    public String addNewManager() {
        Manager managerInStore = managerService.getByUsername(manager.getUsername());
        if (managerInStore == null) {
            managerService.save(manager);
        } else {
            showMessage("Username is exist");
            return "views/manager/addNewManager";
        }
        return "views/manager/listManagers";
    }

    public String searchManager(String firstName, String lastName) throws IOException {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        manager = managerService.getByUsername(username);
        managers = new ArrayList<Manager>() {{
            add(manager);
        }};
        return "views/manager/listManagers";
    }

    private void showMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary));
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

}