package pl.sdacademy.hibernate.sakila.workshop12;

import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.Scanner;

/**
 * Warsztat 12 – zmiana stanu obiektu
 * • Przygotuj metodę zmieniającą kraj według podanego identyfikatora
 * i nazwy, niech zwraca true jeśli istniał i false jeśli nie (find(...)
 * zwróci null)
 * • Zmień stan zarządzanego obiektu w trakcie transakcji używając zwykłego settera
 * • Przetestuj następujące scenariusze:
 * • Kraj nie istnieje (ma zwrócić false)
 * • Dowolny inny kraj (ma go zmienić i zwrócić true)
 */
public class Workshop12 {
    public static void main(String[] args) {
        System.out.println("Podaj id kraju:");
        final int countryId = new Scanner(System.in).nextInt();

        System.out.println("Podaj nazwę kraju:");
        final String countryName = new Scanner(System.in).nextLine();

        boolean updated = updateCountry(countryId, countryName);

        System.out.println(updated);
    }

    public static boolean updateCountry(int countryId, String countryName) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            Country country = em.find(Country.class, countryId);

            if (country == null) {
                return false;
            }

            em.getTransaction().begin();

            country.setName(countryName);

            em.getTransaction().commit();


        }
        finally {
            emf.close();
        }

        return true;
    }
}
