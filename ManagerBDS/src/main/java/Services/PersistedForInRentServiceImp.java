package Services;


import Entities.Persisted;
import Entities.PersistedForInRent;
import Entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class PersistedForInRentServiceImp implements BaseService<PersistedForInRent> {
    private Response response = new Response();
    private ManagerService managerService;

    public PersistedForInRentServiceImp(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public void save(PersistedForInRent object, EntityManagerFactory entityManagerFactory) {
        EntityManager manager = entityManagerFactory.createEntityManager();
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
    public void handleAdd(EntityManagerFactory entityManagerFactory) {
        Scanner sc = new Scanner(System.in);
        Persisted persisted = new Persisted();
        persisted.add(sc);
        System.out.println("Enter timer rent : ");
        String timerRent = sc.nextLine();
        System.out.println("Enter tenant name : ");
        String tenantName = sc.nextLine();
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        Manager manager = managerService.getByUsername(username, entityManagerFactory);
        if (manager == null) {
            System.out.println("Not found username : " + username);
            return;
        }
        PersistedForInRent persistedForInRent = new PersistedForInRent(timerRent, tenantName, persisted.getId(),
                persisted.getAddress(), persisted.getDescription(), persisted.getNumberOfBed(), persisted.getPrice(), manager);
        this.save(persistedForInRent, entityManagerFactory);
        System.out.println(persistedForInRent.toString());
    }
}
