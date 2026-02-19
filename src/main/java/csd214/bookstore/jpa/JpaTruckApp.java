package csd214.bookstore.jpa;

import csd214.bookstore.entities.TruckEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaTruckApp {
    public static void main(String[] args) {
        // 1. Initialize the Engine
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Creating a new Truck...");
            em.getTransaction().begin();
            TruckEntity myTruck = new TruckEntity("Ford", "F-150", 2026, 1000, 12000.0);
            em.persist(myTruck); // Tells Hibernate to save the object
            em.getTransaction().commit();
            System.out.println("Truck saved with Database ID: " + myTruck.getId());

            // --- READ (List All) ---
            listTrucks(em, "[Step 2] Current Inventory:");

            // --- UPDATE (Find & Edit) ---
            System.out.println("\n[Step 3] Editing Truck Price...");
            em.getTransaction().begin();

            // We use the ID to find the specific record
            TruckEntity truckToEdit = em.find(TruckEntity.class, myTruck.getId());
            if (truckToEdit != null) {
                truckToEdit.setPrice(49999.99); // Change the Java field
                // Note: We don't call "update". Hibernate detects the change
                // automatically when we commit (Dirty Checking).
            }

            em.getTransaction().commit();
            listTrucks(em, "[Step 4] After Price Update:");

            // --- DELETE ---
            System.out.println("\n[Step 5] Deleting the Truck...");
            em.getTransaction().begin();

            TruckEntity truckToDelete = em.find(TruckEntity.class, myTruck.getId());
            if (truckToDelete != null) {
                em.remove(truckToDelete); // Tells Hibernate to delete the row
            }

            em.getTransaction().commit();
            listTrucks(em, "[Step 6] Final Inventory (should be empty):");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Always close your resources
            em.close();
            emf.close();
        }
    }

    /**
     * Helper method to run a JPQL query and print results
     */
    private static void listTrucks(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<TruckEntity> trucks = em.createQuery("SELECT t FROM TruckEntity t", TruckEntity.class).getResultList();
        if (trucks.isEmpty()) {
            System.out.println("No trucks found in database.");
        } else {
            trucks.forEach(t -> System.out.println(" > " + t));
        }
    }
}