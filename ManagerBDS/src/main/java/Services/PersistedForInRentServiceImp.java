package Services;


import Entities.Persisted;
import Entities.PersistedForInRent;
import Entities.Manager;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistedForInRentServiceImp implements BaseService<PersistedForInRent> {
    private Response response = new Response();
    private ManagerService managerService;
    private EntityManager entityManager;

    public PersistedForInRentServiceImp(ManagerService managerService) {
        this.managerService = managerService;
        this.entityManager = HibernateHelper.getEntityManager();
    }

    @Override
    public void save(PersistedForInRent object ) {
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
            response.onSuccessAddRent();
        } catch (Exception e) {
            response.onFailAddRent(e.getMessage());
        } finally {
            entityManager.close();
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

    @Override
    public List<PersistedForInRent> getAll() {
        
        List<PersistedForInRent> persistedForInRents = new ArrayList<>();
        try {
            persistedForInRents = entityManager.createQuery("Select r from PersistedForInRent r", PersistedForInRent.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            entityManager.close();
        }
        return persistedForInRents;
    }
}
