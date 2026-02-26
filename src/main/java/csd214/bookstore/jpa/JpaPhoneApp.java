package csd214.bookstore.jpa;

import csd214.bookstore.entities.PhoneEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaPhoneApp {
    public static void main(String[] args) {
        // 1. Initialize the Engine
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Creating a new Phone...");
            em.getTransaction().begin();
            PhoneEntity myPhone = new PhoneEntity("iPhone", 12, true, 999.99, 8);
            em.persist(myPhone); // Tells Hibernate to save the object
            em.getTransaction().commit();
            System.out.println("Phone saved with Database ID: " + myPhone.getId());

            // --- READ (List All) ---
            listPhones(em, "[Step 2] Current Inventory:");

            // --- UPDATE (Find & Edit) ---
            System.out.println("\n[Step 3] Editing Phone Price...");
            em.getTransaction().begin();

            // We use the ID to find the specific record
            PhoneEntity phoneToEdit = em.find(PhoneEntity.class, myPhone.getId());
            if (phoneToEdit != null) {
                phoneToEdit.setPrice(899.99); // Change the Java field
                // Note: We don't call "update". Hibernate detects the change
                // automatically when we commit (Dirty Checking).
            }

            em.getTransaction().commit();
            listPhones(em, "[Step 4] After Price Update:");

            // --- DELETE ---
            System.out.println("\n[Step 5] Deleting the Phone...");
            em.getTransaction().begin();

            PhoneEntity phoneToDelete = em.find(PhoneEntity.class, myPhone.getId());
            if (phoneToDelete != null) {
                em.remove(phoneToDelete); // Tells Hibernate to delete the row
            }

            em.getTransaction().commit();
            listPhones(em, "[Step 6] Final Inventory (should be empty):");

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
    private static void listPhones(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<PhoneEntity> phones = em.createQuery("SELECT p FROM PhoneEntity p", PhoneEntity.class).getResultList();
        if (phones.isEmpty()) {
            System.out.println("No phones found in database.");
        } else {
            phones.forEach(t -> System.out.println(" > " + t));
        }
    }
}
