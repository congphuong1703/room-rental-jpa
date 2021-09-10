package Views;

import Entities.Manager;
import Entities.Persisted;
import Services.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ManagerService managerService = new ManagerServiceImp();
        BaseService persistedSaleService = new PersistedSaleServiceImp(managerService);
        BaseService persistedRentService = new PersistedRentServiceImp(managerService);
        BaseService persistedForInRentService = new PersistedForInRentServiceImp(managerService);
        PersistedService persistedService = new PersistedServiceImp();
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        int choose;
        boolean isLogged = false;

        do {
            System.out.println("****************************");
            System.out.println("Add Manager ................1");
            System.out.println("Add Persisted Sale..........2");
            System.out.println("Add Persisted Rent..........3");
            System.out.println("Add Persisted For In Rent...4");
            System.out.println("Login.......................5");
            System.out.println("****************************");
            System.out.println("\nEnter your option: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("------------Add Manager------------");
                    managerService.handleAdd(factory);
                    break;
                case 2:
                    System.out.println("------------Add Persisted Sale------------");
                    persistedSaleService.handleAdd(factory);
                    break;
                case 3:
                    System.out.println("------------Add Persisted Rent------------");
                    persistedRentService.handleAdd(factory);
                    break;
                case 4:
                    System.out.println("------------Add Persisted For In Rent------------");
                    persistedForInRentService.handleAdd(factory);
                    break;
                case 5:
                    System.out.println("Enter username : ");
                    String username = sc.nextLine();
                    System.out.println("Enter password : ");
                    String password = sc.nextLine();
                    Manager manager = managerService.handleLogin(username, password, factory);
                    if (manager == null)
                        System.out.println("Login fail");
                    else
                        isLogged = true;
                default:
                    break;
            }
        } while (!isLogged);

        System.out.println("Login successful");
        System.out.println("======================");
        System.out.println("Persisted Properties");
        persistedService.showAll(factory);
        System.out.println("Persisted property Managers");
        persistedService.showAllAllocation(factory);
        System.out.println("Persisted Property Allocations");
        managerService.showAll(factory);

        do {
            System.out.println("\n****************************");
            System.out.println("Query a Property...........1");
            System.out.println("Query a Property Manager...2");
            System.out.println("Query Allocations..........3");
            System.out.println("Exit.......................4");
            System.out.println("****************************");

            System.out.println("\n Enter your option: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    persistedService.getByAddress(factory);
                    break;
                case 2:
                    managerService.showInfoByUsername(factory);
                    break;
                case 3:
                    persistedService.getByAllocation(factory);
                    break;
                default:
                    System.exit(0);
            }
        } while (true);
    }
}
