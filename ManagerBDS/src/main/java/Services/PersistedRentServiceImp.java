package Services;

import Entities.Persisted;
import Entities.PersistedRent;
import Entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class PersistedRentServiceImp implements BaseService<PersistedRent> {

    private Response response = new Response();
    private EntityManagerFactory factory;

    public PersistedRentServiceImp() {
        factory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public void save(PersistedRent object ) {
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
        /*Scanner sc = new Scanner(System.in);
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
                 persisted.getDescription(), persisted.getNumberOfBed(), persisted.getPrice(), manager, timerRent);
        this.save(rent);
        System.out.println(rent.toString());*/
    }

    @Override
    public List<PersistedRent> getAll() {
        return null;
    }

}
