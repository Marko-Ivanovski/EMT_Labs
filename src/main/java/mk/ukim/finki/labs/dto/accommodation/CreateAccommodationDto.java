package mk.ukim.finki.labs.dto.accommodation;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.model.enumerations.Category;

public record CreateAccommodationDto (String name, Category category, Long hostId, Integer numRooms) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, false);
    }
}
