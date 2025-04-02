package mk.ukim.finki.labs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "host_like",
            joinColumns = @JoinColumn(name = "host_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    @JsonIgnore
    private List<Guest> guests;

    public Host() {}

    public Host(String name, String surname, Country country, List<Guest> guests) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.guests = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
}
