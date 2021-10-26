package Services;

import Entities.Persisted;
import Entities.PersistedRent;
import Entities.PersistedRent;
import Entities.Manager;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistedRentServiceImp implements BaseService<PersistedRent> {

    private Response response = new Response();
    private ManagerService managerService;
    private EntityManager entityManager;

    public PersistedRentServiceImp(ManagerService managerService) {
        this.managerService = managerService;
        this.entityManager = HibernateHelper.getEntityManager();
    }

    @Override
    public void save(PersistedRent object) {
        
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
        System.out.println("Enter username");
        String username = sc.nextLine();
        Manager manager = managerService.getByUsername(username);
        if (manager == null) {
            System.out.println("Not found username");
            return;
        }
        PersistedRent rent = new PersistedRent(persisted.getId(),
                persisted.getAddress(), persisted.getDescription(), persisted.getNumberOfBed(), persisted.getPrice(), manager, timerRent);
        this.save(rent);
        System.out.println(rent.toString());
    }

    @Override
    public List<PersistedRent> getAll() {
        
        List<PersistedRent> persistedRents = new ArrayList<>();
        try {
            persistedRents = entityManager.createQuery("Select r from PersistedRent r", PersistedRent.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            entityManager.close();
        }
        return persistedRents;
    }

}
