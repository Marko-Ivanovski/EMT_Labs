package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.country.CreateCountryDto;
import mk.ukim.finki.labs.dto.country.DisplayCountryDto;
import mk.ukim.finki.labs.model.domain.Country;
import mk.ukim.finki.labs.service.domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationService {

    private final CountryDomainService countryDomainService;

    public CountryApplicationService(CountryDomainService countryDomainService) {
        this.countryDomainService = countryDomainService;
    }

    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryDomainService.findAll());
    }

    public Optional<DisplayCountryDto> findById(Long id) {
        return countryDomainService.findById(id)
                .map(DisplayCountryDto::from);
    }

    public DisplayCountryDto create(CreateCountryDto dto) {
        Country country = dto.toCountry();
        countryDomainService.save(country);
        return DisplayCountryDto.from(country);
    }

    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto dto) {
        Country updated = dto.toCountry();
        return countryDomainService.update(id, updated)
                .map(DisplayCountryDto::from);
    }

    public void delete(Long id) {
        countryDomainService.delete(id);
    }
}
