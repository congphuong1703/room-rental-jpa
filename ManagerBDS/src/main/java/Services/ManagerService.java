package Services;

import Entities.Manager;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface ManagerService extends BaseService<Manager>{

    Manager getByUsername(String username, EntityManagerFactory entityManagerFactory);

    Manager handleLogin(String username, String password, EntityManagerFactory factory);

    void showAll(EntityManagerFactory factory);

    void showInfoByUsername(EntityManagerFactory factory);

    List<Manager> getAll(EntityManagerFactory factory);
}
