package csd214.bookstore.jpa;

import csd214.bookstore.entities.LaptopEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaLaptopApp {
    public static void main(String[] args) {
        // 1. Initialize the Engine
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Creating a new Laptop...");
            em.getTransaction().begin();
            LaptopEntity myLaptop = new LaptopEntity("Mac", 12, 16.5, 1499.99, 5);
            em.persist(myLaptop); // Tells Hibernate to save the object
            em.getTransaction().commit();
            System.out.println("Laptop saved with Database ID: " + myLaptop.getId());

            // --- READ (List All) ---
            listLaptops(em, "[Step 2] Current Inventory:");

            // --- UPDATE (Find & Edit) ---
            System.out.println("\n[Step 3] Editing Laptop Price...");
            em.getTransaction().begin();

            // We use the ID to find the specific record
            LaptopEntity laptopToEdit = em.find(LaptopEntity.class, myLaptop.getId());
            if (laptopToEdit != null) {
                laptopToEdit.setPrice(899.99); // Change the Java field
                // Note: We don't call "update". Hibernate detects the change
                // automatically when we commit (Dirty Checking).
            }

            em.getTransaction().commit();
            listLaptops(em, "[Step 4] After Price Update:");

            // --- DELETE ---
            System.out.println("\n[Step 5] Deleting the Laptop...");
            em.getTransaction().begin();

            LaptopEntity laptopToDelete = em.find(LaptopEntity.class, myLaptop.getId());
            if (laptopToDelete != null) {
                em.remove(laptopToDelete); // Tells Hibernate to delete the row
            }

            em.getTransaction().commit();
            listLaptops(em, "[Step 6] Final Inventory (should be empty):");

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
    private static void listLaptops(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<LaptopEntity> laptops = em.createQuery("SELECT l FROM LaptopEntity l", LaptopEntity.class).getResultList();
        if (laptops.isEmpty()) {
            System.out.println("No laptops found in database.");
        } else {
            laptops.forEach(t -> System.out.println(" > " + t));
        }
    }
}