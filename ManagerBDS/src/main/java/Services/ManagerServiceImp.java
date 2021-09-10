package Services;

import Entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerServiceImp implements ManagerService {
    private Response response = new Response();

    @Override
    public void save(Manager object, EntityManagerFactory factory) {
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
    public List<Manager> getAll(EntityManagerFactory factory) {
        EntityManager manager = factory.createEntityManager();
        List<Manager> managers = new ArrayList<>();
        try {
            managers = manager.createQuery("Select r from Manager r", Manager.class).getResultList();

        } catch (Exception ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            manager.close();
        }
        return managers;
    }

    @Override
    public void handleAdd(EntityManagerFactory factory) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        System.out.println("Enter password : ");
        String password = sc.nextLine();
        System.out.println("Enter email : ");
        String email = sc.nextLine();
        System.out.println("Enter phone number : ");
        String phoneNumber = sc.nextLine();
        Manager manager = new Manager(username, phoneNumber, email, password);
        this.save(manager, factory);
        System.out.println(manager.toString());
    }

    @Override
    public Manager getByUsername(String username, EntityManagerFactory factory) {
        EntityManager entityManager = factory.createEntityManager();
        List<Manager> managers = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT m FROM Manager m WHERE m.username = :username", Manager.class);

            managers = query.setParameter("username", username).getResultList();

        } catch (Exception ex) {
            System.out.println(" " + ex.getMessage());
        } finally {
            entityManager.close();
        }
        return managers.isEmpty() ? null : managers.get(0);
    }

    public Manager handleLogin(String username, String password, EntityManagerFactory factory) {
        EntityManager manager = factory.createEntityManager();

        Query query = manager.createQuery("SELECT u FROM Manager u where u.username = :username and u.password = :password", Manager.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Manager> managers = query.getResultList();

        if (managers.isEmpty())
            return null;
        return managers.get(0);
    }

    public void showAll(EntityManagerFactory factory) {
        List<Manager> managers = this.getAll(factory);
        for (Manager manager : managers) {
            System.out.println("\n");
            System.out.println(manager.toString());
        }

    }

    public void showInfoByUsername(EntityManagerFactory factory) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();
        Manager manager = this.getByUsername(name, factory);
        System.out.println(manager.toString());
        System.out.println("\n -------------\n");
    }

}
