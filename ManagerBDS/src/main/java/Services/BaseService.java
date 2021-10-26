package Services;

import javax.persistence.EntityManagerFactory;

public interface BaseService<T> {

    void save(T object);

    void handleAdd();

}
