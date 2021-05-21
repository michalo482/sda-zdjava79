package pl.sdacademy.hibernate.sakila.workshop15;

import pl.sdacademy.hibernate.sakila.workhop6.City;
import pl.sdacademy.hibernate.sakila.workhop6.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Warsztat 15 – użycie kaskady w nowym obiekcie
 * • Przygotuj metodę przyjmującą nazwę kraju i nazwę miasta, niech
 * zwraca obiekt zarządzany
 * • Zmodyfikuj obiekt miasta tak, by kaskadowo tworzył obiekt kraju
 * • Utwórz obiekt kraju z podaną nazwą
 * • Utwórz i utrwal obiekt miasta o podanej nazwie w danym kraju
 * • Przetestuj działanie programu
 */
public class Workshop15 {
    public static void main(String[] args) {
        System.out.println("Podaj nazwę kraju:");
        final String countryName = new Scanner(System.in).nextLine();

        System.out.println("Podaj nazwę miasta:");
        final String cityName = new Scanner(System.in).nextLine();

        City city = createCity(countryName, cityName);
        System.out.println(city);
    }

    public static City createCity(String countryName, String cityName) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            Country country = new Country();
            country.setName(countryName);

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
