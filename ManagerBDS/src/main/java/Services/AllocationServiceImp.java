package Services;

import Entities.Allocation;
import Entities.Persisted;
import Entities.PersistedRent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllocationServiceImp implements BaseService<Allocation> {
    private EntityManagerFactory factory;
    private Response response = new Response();

    public AllocationServiceImp() {
        factory = Persistence.createEntityManagerFactory("default");

    }

    @Override
    public void save(Allocation object) {
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

    }

    @Override
    public List<Allocation> getAll() {
        return null;
    }


}
