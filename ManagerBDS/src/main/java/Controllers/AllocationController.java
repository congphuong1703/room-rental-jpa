package Controllers;

import Entities.Allocation;
import Entities.Persisted;
import Services.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "allocationController", eager = true)
@RequestScoped
public class AllocationController {

    private List<Allocation> allocationList = new ArrayList<>();
    private Persisted persisted = new Persisted();
    private Allocation allocation = new Allocation();
    private PersistedService persistedService = new PersistedServiceImp();
    private ManagerService managerService = new ManagerServiceImp();
    private BaseService saleService = new PersistedSaleServiceImp(managerService);
    private BaseService rentService = new PersistedRentServiceImp();
    private BaseService inRentService = new PersistedForInRentServiceImp(managerService);
    private BaseService allocationService = new AllocationServiceImp();

    public String listAllocations() {
        allocationList = allocationService.getAll();
        return "views/allocation/listAllocations";
    }

    public String addNewAllocation() {
        Persisted persistedInStorage = persistedService.getById(persisted.getId());
        allocation = new Allocation(persistedInStorage);
        allocation.setTimCreated(LocalDate.now());
        return "views/allocation/listAllocations";
    }

    public String detailAllocation() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        try {
            Persisted persistedInStorage = persistedService.getById(Long.valueOf(id));
            if (persistedInStorage != null && persistedInStorage instanceof Allocation) {
                allocation = (Allocation) persistedInStorage;
            } else {
                showMessage("Not exists allocation id : " + id);
                return "views/allocation/detailAllocation";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "views/allocation/detailAllocation";
    }

    private void showMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary));
    }

    private String searchAllocation(Long id){
        return "views/allocation/listAllocations";
    }

    public String deleteAllocation() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        Persisted persistedInStorage = persistedService.getById(Long.valueOf(id));
        if (persistedInStorage != null && persistedInStorage instanceof Allocation) {
            persistedService.delete(Long.valueOf(id));
        } else {
            showMessage("Not exists allocation id : " + id);
        }
        return "views/allocation/listAllocations";
    }

    public List<Allocation> getAllocationList() {
        return allocationList;
    }

    public void setAllocationList(List<Allocation> allocationList) {
        this.allocationList = allocationList;
    }

    public Persisted getPersisted() {
        return persisted;
    }

    public void setPersisted(Persisted persisted) {
        this.persisted = persisted;
    }

    public Allocation getAllocation() {
        return allocation;
    }

    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }

    public PersistedService getPersistedService() {
        return persistedService;
    }

    public void setPersistedService(PersistedService persistedService) {
        this.persistedService = persistedService;
    }
}
