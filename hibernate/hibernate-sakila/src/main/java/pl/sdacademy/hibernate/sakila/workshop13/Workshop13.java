package pl.sdacademy.hibernate.sakila.workshop13;

import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Warsztat 13 – merge
 * • Przygotuj metodę przyjmującą identyfikator i nazwę kraju, niech
 * zwraca obiekt zarządzany
 * • Utwórz obiekt kraju o podanej nazwie i id (dodaj setter jeśli nie ma)
 * • Wykonaj na nim operację merge w transakcji
 * • Przetestuj następujące scenariusze:
 * • Kraj nie istnieje (ma powstać nowy)
 * • Kraj istnieje (ma zostać zmieniony)
 */
public class Workshop13 {
    public static void main(String[] args) {
        System.out.println("Podaj id kraju:");
        final int countryId = new Scanner(System.in).nextInt();

        System.out.println("Podaj nazwę kraju:");
        final String countryName = new Scanner(System.in).nextLine();

        Country country = addOrUpdateCountry(countryId, countryName);
        System.out.println(country);
    }

    public static Country addOrUpdateCountry(int countryId, String countryName) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            Country country = new Country();
            country.setCountryId(countryId);
            country.setName(countryName);

            em.getTransaction().begin();

            Country mergedCountry = em.merge(country);

            em.getTransaction().commit();

            return mergedCountry;
        }
        finally {
            emf.close();
        }
    }
}
