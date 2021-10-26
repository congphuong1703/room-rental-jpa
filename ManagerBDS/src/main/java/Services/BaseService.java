package Services;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface BaseService<T> {

    void save(T object);

    void handleAdd();

    List<T> getAll();

}
