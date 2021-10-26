package Services;

import Entities.Manager;

import java.util.List;

public interface ManagerService extends BaseService<Manager>{

    Manager getByUsername(String username);

    Manager handleLogin(String username, String password );

    void showAll();

    void showInfoByUsername();

    List<Manager> getAll();
}
