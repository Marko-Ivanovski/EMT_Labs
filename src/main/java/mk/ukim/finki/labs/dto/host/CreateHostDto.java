package mk.ukim.finki.labs.dto.host;

import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.model.domain.Country;

import java.util.ArrayList;

public record CreateHostDto(String name, String surname, Long countryId) {
    public Host toHost(Country country) {
        return new Host(name, surname, country, new ArrayList<>());
    }
}
