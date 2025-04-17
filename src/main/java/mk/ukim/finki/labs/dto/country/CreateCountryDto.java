package mk.ukim.finki.labs.dto.country;

import mk.ukim.finki.labs.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
