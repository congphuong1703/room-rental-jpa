package Services;

import javax.persistence.EntityManagerFactory;

public interface BaseService<T> {

    void save(T object, EntityManagerFactory factory);

    void handleAdd(EntityManagerFactory entityManagerFactory);

}
