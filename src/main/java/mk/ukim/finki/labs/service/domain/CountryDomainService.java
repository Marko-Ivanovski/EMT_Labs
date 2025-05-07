package mk.ukim.finki.labs.service.domain;

import mk.ukim.finki.labs.model.domain.Country;
import mk.ukim.finki.labs.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDomainService {

    private final CountryRepository countryRepository;

    public CountryDomainService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    public void save(Country country) {
        countryRepository.save(country);
    }

    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    public Optional<Country> update(Long id, Country updatedCountry) {
        return countryRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedCountry.getName());
                    existing.setContinent(updatedCountry.getContinent());
                    return countryRepository.save(existing);
                });
    }
}
