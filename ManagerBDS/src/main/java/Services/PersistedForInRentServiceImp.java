package Services;


import Entities.Persisted;
import Entities.PersistedForInRent;
import Entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class PersistedForInRentServiceImp implements BaseService<PersistedForInRent> {
    private Response response = new Response();
    private ManagerService managerService;
    private EntityManagerFactory factory;

    public PersistedForInRentServiceImp(ManagerService managerService) {
        factory = Persistence.createEntityManagerFactory("default");
        this.managerService = managerService;
    }

    @Override
    public void save(PersistedForInRent object ) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(object);
            manager.getTransaction().commit();
            response.onSuccessAddRent();
        } catch (Exception e) {
            response.onFailAddRent(e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public void handleAdd() {
        Scanner sc = new Scanner(System.in);
        Persisted persisted = new Persisted();
        persisted.add(sc);
        System.out.println("Enter timer rent : ");
        String timerRent = sc.nextLine();
        System.out.println("Enter tenant name : ");
        String tenantName = sc.nextLine();
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        Manager manager = managerService.getByUsername(username);
        if (manager == null) {
            System.out.println("Not found username : " + username);
            return;
        }
        PersistedForInRent persistedForInRent = new PersistedForInRent(timerRent, tenantName, persisted.getId(),
                persisted.getAddress(), persisted.getDescription(), persisted.getNumberOfBed(), persisted.getPrice(), manager);
        this.save(persistedForInRent);
        System.out.println(persistedForInRent.toString());
    }
}
