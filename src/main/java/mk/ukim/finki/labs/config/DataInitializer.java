package mk.ukim.finki.labs.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labs.model.enumerations.Category;
import org.springframework.stereotype.Component;
import mk.ukim.finki.labs.model.*;
import mk.ukim.finki.labs.repository.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final GuestRepository guestRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository, GuestRepository guestRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.guestRepository = guestRepository;
    }

    @PostConstruct
    public void init() {
        Country country = new Country("Macedonia", "Europe");
        country = countryRepository.save(country);

        Host host1 = new Host("John", "Doe", country, new ArrayList<>());
        host1 = hostRepository.save(host1);

        Host host2 = new Host("Test", "Testovski", country, new ArrayList<>());
        host2 = hostRepository.save(host2);

        Host host3 = new Host("Alice", "Johnson", country, new ArrayList<>());
        host3 = hostRepository.save(host3);

        Host host4 = new Host("Bob", "Williams", country, new ArrayList<>()); // No guests
        host4 = hostRepository.save(host4);

        Host host5 = new Host("Emma", "Brown", country, new ArrayList<>()); // No guests
        host5 = hostRepository.save(host5);

        List<Host> associatedHosts = new ArrayList<>();
        associatedHosts.add(host1);
        associatedHosts.add(host3);

        Guest guest1 = new Guest("Marko", "Ivanovski", country, associatedHosts);
        guest1 = guestRepository.save(guest1);

        host1.getGuests().add(guest1);
        host3.getGuests().add(guest1);

        hostRepository.save(host1);
        hostRepository.save(host3);

        Accommodation accommodation = new Accommodation("Cozy Apartment", Category.APARTMENT, host1, 2, false);
        accommodationRepository.save(accommodation);

        System.out.println("Data initialized");
    }
}
