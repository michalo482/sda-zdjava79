package pl.sdacademy.hibernate.sakila.workshop14;

import com.sun.source.tree.CompilationUnitTree;
import pl.sdacademy.hibernate.sakila.workhop6.City;
import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Warsztat 14 – użycie referencji w nowym obiekcie
 * • Przygotuj metodę przyjmującą identyfikator kraju i nazwę miasta,
 * niech zwraca obiekt zarządzany
 * • Uzyskaj obiekt kraju przez referencję
 * • Utwórz i utrwal obiekt miasta o podanej nazwie używając referencji
 * do kraju
 * • Przetestuj działanie programu
 */
public class Workshop14 {
    public static void main(String[] args) {
        System.out.println("Podaj id kraju:");
        final int countryId = new Scanner(System.in).nextInt();

        System.out.println("Podaj nazwę miasta:");
        final String cityName = new Scanner(System.in).nextLine();

        City city = createCity(countryId, cityName);
        System.out.println(city);
    }

    public static City createCity(int countryId, String cityName) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            Country country = em.getReference(Country.class, countryId);

            City city = new City();
            city.setCountry(country);
            city.setName(cityName);

            em.getTransaction().begin();

            em.persist(city);

            em.getTransaction().commit();

            return city;

        }
        finally {
            emf.close();
        }
    }
}
