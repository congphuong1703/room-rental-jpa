package Services;

import Entities.*;
import Entities.Persisted;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistedServiceImp implements PersistedService {

    private EntityManager entityManager;
    public PersistedServiceImp() {
        this.entityManager = HibernateHelper.getEntityManager();
    }

    @Override
    public void showAll() {
        List<Persisted> persistedSales = this.getAll();
        for (Persisted persisted : persistedSales) {
            System.out.println("\n");
            this.showInfo(persisted);
        }

    }

    @Override
    public List<Persisted> getAll() {

        List<Persisted> persistedList = new ArrayList<>();
        try {
            persistedList = entityManager.createQuery("Select r from Persisted r", Persisted.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            entityManager.close();
        }
        return persistedList;
    }

    @Override
    public void getByAllocation() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name : ");
        String username = sc.nextLine();
        System.out.println("Allocation ID : ");
        Long allocationId = sc.nextLong();
        sc.nextLine();
        Query query = entityManager.createQuery("SELECT r FROM Persisted r where  r.id = :id and r.manager.username = :username", Persisted.class);
        query.setParameter("username", username);
        query.setParameter("id", allocationId);

        List<Persisted> persistedList = query.getResultList();

        try {
            for (Persisted persisted : persistedList) {
                System.out.println("\n");
                this.showInfo(persisted);
            }
        } catch (Exception ex) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entityManager.close();
        }

        for (Persisted persisted : persistedList) {
            System.out.println("\n");
            System.out.println("Manger Name :  " + persisted.getUser().getUsername() + " <==> Property Location : " + persisted.getAddress());
        }
    }

    @Override
    public void showAllAllocation() {

        List<Persisted> persistedList = new ArrayList<>();
        try {
            persistedList = entityManager.createQuery("Select r from Persisted r", Persisted.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            entityManager.close();
        }
        for (Persisted persisted : persistedList) {
            System.out.println("\n");
            System.out.println("Manger Name :  " + persisted.getUser().getUsername() + " <==> Property Location : " + persisted.getAddress());
        }
    }

    @Override
    public void getByAddress() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter address : ");
        String address = sc.nextLine();
        Query query = entityManager.createQuery("SELECT r FROM Persisted r where r.address = :address", Persisted.class);
        List<Persisted> persistedList = query.setParameter("address", address).getResultList();

        try {
            for (Persisted persisted : persistedList) {
                System.out.println("\n");
                this.showInfo(persisted);
            }
        } catch (Exception ex) {
            Logger.getLogger(Persisted.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entityManager.close();
        }
    }

    private void showInfo(Persisted persisted) {
        if (persisted instanceof PersistedRent) {
            PersistedRent object = (PersistedRent) persisted;
            System.out.println("For Rent:");
            System.out.println(object.toString());
        } else if (persisted instanceof PersistedSale) {
            PersistedSale object = (PersistedSale) persisted;
            System.out.println("For Sale:");
            System.out.println(object.toString());
        } else {
            PersistedForInRent object = (PersistedForInRent) persisted;
            System.out.println("For In Rent Property:");
            System.out.println(object.toString());
        }
    }

}
