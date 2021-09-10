package Services;

import Entities.Manager;
import Entities.Persisted;
import Entities.PersistedSale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class PersistedSaleServiceImp implements BaseService<PersistedSale> {
    private Response response = new Response();
    private ManagerService managerService;

    public PersistedSaleServiceImp(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public void save(PersistedSale object, EntityManagerFactory entityManagerFactory) {
        EntityManager manager = entityManagerFactory.createEntityManager();
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
    public void handleAdd(EntityManagerFactory entityManagerFactory) {
        Scanner sc = new Scanner(System.in);
        Persisted persisted = new Persisted();
        persisted.add(sc);
        double area = this.inputArea(sc);
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        Manager manager = managerService.getByUsername(username, entityManagerFactory);
        if (manager == null) {
            System.out.println("Not found username");
            return;
        }
        PersistedSale sale = new PersistedSale(persisted.getId(),
                persisted.getAddress(), persisted.getDescription(), persisted.getNumberOfBed(), persisted.getPrice(), manager, area);
        this.save(sale, entityManagerFactory);
        System.out.println(sale.toString());
    }

    private double inputArea(Scanner sc) {
        double area = 0;
        boolean beakLoop = false;
        while (!beakLoop) {
            try {
                System.out.println("Enter area : ");
                area = Double.parseDouble(sc.nextLine());
                if (area < 0) {
                    System.out.println("Price area can't less than 0!");
                    throw new NumberFormatException();
                }
                beakLoop = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please input number type");
            }
        }
        return area;
    }
}
