package pl.sdacademy.hibernate.sakila.workhop6;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    @Column(name = "city")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

    public City() {
    }

    public City(String name, Country country, List<Address> addresses) {
        this.name = name;
        this.country = country;
        this.addresses = addresses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
