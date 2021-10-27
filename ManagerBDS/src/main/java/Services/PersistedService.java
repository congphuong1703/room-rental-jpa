package Services;


import Entities.Persisted;

import java.util.List;

public interface PersistedService {

    void showAllAllocation();

    Persisted getById(Long id);

    void getByAddress();

    void showAll();

    void delete(Long id);

    List<Persisted> getAll();

    void getByAllocation();

}
