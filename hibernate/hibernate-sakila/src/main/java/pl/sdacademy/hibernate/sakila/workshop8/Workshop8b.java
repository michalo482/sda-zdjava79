package pl.sdacademy.hibernate.sakila.workshop8;

import pl.sdacademy.hibernate.sakila.workhop6.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Warsztat 8b – ładowanie krajów, miast i adresów
 * • Przygotuj zapytanie JPQL ładujące wszystkie kraje
 * • Przyjrzyj się wygenerowanym zapytaniom SQL
 * • W jaki sposób najlepiej rozwiązać problem który wystąpił?
 */
public class Workshop8b {
    public static void main(String[] args) {
        final List<Country> countries = findAllCountries();
        System.out.println(countriesToString(countries));
    }

    public static String countriesToString(List<Country> countries) {
        final StringBuilder sb = new StringBuilder();
        for (Country country : countries) {
            sb.append(country.getName()).append("\n");
            for (City city : country.getCities()) {
                sb.append("\t").append(city.getName()).append("\n");
                for (Address address : city.getAddresses()) {
                    sb.append("\t\t").append(address.getAddress()).append("\n");
                }
            }
        }

        return sb.toString();
    }

    public static List<Country> findAllCountries() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SakilaPU");
        final EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Country> countriesQuesry = em.createQuery(
                    "SELECT c FROM Country c JOIN FETCH c.cities cit JOIN FETCH cit.addresses ORDER BY c.name", Country.class
                    );

            List<Country> resultList = countriesQuesry.getResultList();
            String countriesString = countriesToString(resultList);

            return resultList;

        } finally {
            emf.close();
        }
    }
}
