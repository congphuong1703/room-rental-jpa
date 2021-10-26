package Services;


import Entities.Persisted;

import java.util.List;

public interface PersistedService {

    void showAllAllocation();

    void getByAddress();

    void showAll();

    List<Persisted> getAll();

    void getByAllocation();

}
