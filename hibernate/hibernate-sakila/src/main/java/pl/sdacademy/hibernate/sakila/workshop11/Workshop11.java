package pl.sdacademy.hibernate.sakila.workshop11;

import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.*;
import java.util.Scanner;

/**
 * Warsztat 11 – usuwanie obiektu
 * • Przygotuj metodę usuwającą kraj według podanego id, zwracającą
 * true jeśli istniał i false jeśli nie (find(...) zwróci null)
 * • Użyj EntityManager.remove(...)na zarządzanym obiekcie
 * • Obsłuż wyjątek PersistenceException – np. wycofanie transakcji i rzucenie wyjątku dalej
 * • Przetestuj następujące scenariusze:
 * • Kraj nie istnieje (zwróć false)
 * • Kraj dodany w poprzednim ćwiczeniu (usuń i zwróć true)
 * • Kraju nie da się usunąć, ponieważ jest używany jako FK (rzuć wyjątek)
 */
public class Workshop11 {
    public static void main(String[] args) {
        System.out.println("Podaj id kraju:");
        final int countryId = new Scanner(System.in).nextInt();

        boolean deleted = deleteCountry(countryId);

        System.out.println(deleted);
    }

    public static boolean deleteCountry(int countryId) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            Country country = em.find(Country.class, countryId);

            if (country == null) {
                return false;
            }

            em.getTransaction().begin();

            try {
                em.remove(country);
            }
            catch (PersistenceException persistenceException) {
                em.getTransaction().rollback();
                throw persistenceException;
            }
            em.getTransaction().commit();


        }
        finally {
            emf.close();
        }

        return true;
    }
}
