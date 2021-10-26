package Controllers;

import Entities.Manager;
import Services.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Khai báo been
@ManagedBean
@RequestScoped
public class ManagerController implements Serializable {

    private Manager manager = new Manager();

    private List<Manager> managers = new ArrayList<>();
    private ManagerService managerService = new ManagerServiceImp();

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

    public String searchManager() throws IOException {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        manager = managerService.getByUsername(username);
        managers = new ArrayList<Manager>() {{
            add(manager);
        }};
        return "views/manager/listManagers";
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

    private void showMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary));
    }

}
