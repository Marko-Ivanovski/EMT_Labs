package mk.ukim.finki.labs.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labs.model.enumerations.Category;
import org.springframework.stereotype.Component;
import mk.ukim.finki.labs.model.*;
import mk.ukim.finki.labs.repository.*;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country country = new Country("Macedonia", "Europe");
        country = countryRepository.save(country);

        Host host = new Host("John", "Doe", country);
        host = hostRepository.save(host);

        Accommodation accommodation = new Accommodation("Cozy Apartment", Category.APARTMENT, host, 2, false);
        accommodationRepository.save(accommodation);

        System.out.println("Data initialized");
    }
}