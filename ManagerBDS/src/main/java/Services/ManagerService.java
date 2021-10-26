package Services;

import Entities.Manager;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface ManagerService extends BaseService<Manager> {

    Manager getByUsername(String username);

    Manager handleLogin(String username, String password);

    void showAll();

    void showInfoByUsername();

    List<Manager> getAll();

    Manager getByFirstOrLastName(String firstName, String lastName);
}
