package mk.ukim.finki.labs.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.Country;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.model.domain.TemporaryReservation;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.model.enumerations.Category;
import mk.ukim.finki.labs.model.enumerations.Role;
import mk.ukim.finki.labs.repository.AccommodationRepository;
import mk.ukim.finki.labs.repository.CountryRepository;
import mk.ukim.finki.labs.repository.HostRepository;
import mk.ukim.finki.labs.repository.TemporaryReservationRepository;
import mk.ukim.finki.labs.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TemporaryReservationRepository temporaryReservationRepository;

    public DataInitializer(
            AccommodationRepository accommodationRepository,
            HostRepository hostRepository,
            CountryRepository countryRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TemporaryReservationRepository temporaryReservationRepository
    ) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.temporaryReservationRepository = temporaryReservationRepository;
    }

    @PostConstruct
    public void init() {
        // Countries
        Country mk = new Country("North Macedonia", "Europe");
        Country fr = new Country("France", "Europe");
        Country us = new Country("USA", "North America");

        mk = countryRepository.save(mk);
        fr = countryRepository.save(fr);
        us = countryRepository.save(us);

        // Hosts
        Host host1 = hostRepository.save(new Host("John", "Doe", mk));
        Host host2 = hostRepository.save(new Host("Emma", "Brown", fr));
        Host host3 = hostRepository.save(new Host("Alice", "Smith", us));
        Host host4 = hostRepository.save(new Host("Bob", "Williams", us));
        Host host5 = hostRepository.save(new Host("Maria", "Garcia", mk));

        // Accommodations
        accommodationRepository.save(new Accommodation("Cozy Apartment", Category.APARTMENT, host1, 2, false));
        accommodationRepository.save(new Accommodation("Luxury House", Category.HOUSE, host2, 4, true));
        accommodationRepository.save(new Accommodation("Modern Room", Category.ROOM, host3, 1, false));
        accommodationRepository.save(new Accommodation("Beach House", Category.HOUSE, host4, 5, false));
        accommodationRepository.save(new Accommodation("Mountain Cabin", Category.APARTMENT, host5, 3, true));

        // Users
        User user = new User("user", passwordEncoder.encode("user"), Role.USER);
        User host = new User("host", passwordEncoder.encode("host"), Role.HOST);
        user = userRepository.save(user);
        userRepository.save(host);

        // Fetch accommodations by name
        Accommodation acc1 = accommodationRepository.findByName("Cozy Apartment").orElseThrow();
        Accommodation acc2 = accommodationRepository.findByName("Luxury House").orElseThrow();
        Accommodation acc3 = accommodationRepository.findByName("Modern Room").orElseThrow();

        TemporaryReservation res1 = new TemporaryReservation(user, acc1);
        TemporaryReservation res2 = new TemporaryReservation(user, acc2);
        TemporaryReservation res3 = new TemporaryReservation(user, acc3);

        temporaryReservationRepository.saveAll(List.of(res1, res2, res3));

        System.out.println("Initial data seeded successfully!");
    }
}
