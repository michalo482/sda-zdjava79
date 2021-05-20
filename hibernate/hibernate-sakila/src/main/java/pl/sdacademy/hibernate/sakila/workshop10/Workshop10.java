package pl.sdacademy.hibernate.sakila.workshop10;

import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Warsztat 10 – utrwalanie nowego obiektu
 * • Użyj bazy Sakila
 * • Zaadnotuj @GeneratedValue identyfikator w klasie Country
 * • Utwórz nową instancję Country i ustaw nazwę (może być fikcyjna)
 * • Używając metody persist(...) w ramach transakcji spróbuj utrwalić ten obiekt
 * • Sprawdź działanie programu i zawartość bazy
 */
public class Workshop10 {
    public static void main(String[] args) {
        System.out.println("Podaj nazwę kraju:");
        final String countryName = new Scanner(System.in).nextLine();

        final Country country = new Country();
        country.setName(countryName);

        System.out.println(country);

        persistCountry(country);

        System.out.println(country);
    }

    public static void persistCountry(Country country) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(country);

            em.getTransaction().commit();
        }
        finally {
            emf.close();
        }
    }
}
