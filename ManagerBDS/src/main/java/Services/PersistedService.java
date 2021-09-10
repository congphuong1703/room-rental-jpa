package Services;


import Entities.Persisted;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface PersistedService {

    void showAllAllocation(EntityManagerFactory factory);

    void getByAddress(EntityManagerFactory factory);

    void showAll(EntityManagerFactory factory);

    List<Persisted> getAll(EntityManagerFactory factory);

    void getByAllocation(EntityManagerFactory factory);

}
