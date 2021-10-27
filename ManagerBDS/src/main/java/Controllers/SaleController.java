package Controllers;

import Entities.Manager;
import Entities.Persisted;
import Entities.PersistedRent;
import Entities.PersistedSale;
import Services.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class SaleController {

    private Persisted persisted = new Persisted();
    private PersistedSale sale = new PersistedSale();

    private List<PersistedSale> sales = new ArrayList<>();
    private ManagerService managerService = new ManagerServiceImp();
    private BaseService saleService = new PersistedSaleServiceImp(managerService);
    private PersistedService persistedService = new PersistedServiceImp();

    public String listSales() {
        sales = saleService.getAll();
        //trả ra file listManager.xhtml
        return "views/sale/listSales";
    }

    public String addNewSale() {
        sale = new PersistedSale(persisted);
        saleService.save(sale);
        return "views/manager/listManagers";
    }

    public String searchSale(Long id) {
        //query get property by id
        Persisted persisted = persistedService.getById(id);
        //check null && type is persistedsale
        if (persisted != null && persisted instanceof PersistedSale) {
            //cast to persisted from persistedsale
            PersistedSale persistedSale = (PersistedSale) persisted;
            //constructor new array with element is new sale property
            sales = new ArrayList<PersistedSale>() {{
                add(persistedSale);
            }};
        } else {
            showMessage("Not found sale property by id : " + id);
        }
        //return view
        return "views/sale/listSales";
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

    public PersistedSale getSale() {
        return sale;
    }

    public void setSale(PersistedSale sale) {
        this.sale = sale;
    }

    public List<PersistedSale> getSales() {
        return sales;
    }

    public void setSales(List<PersistedSale> sales) {
        this.sales = sales;
    }
}
