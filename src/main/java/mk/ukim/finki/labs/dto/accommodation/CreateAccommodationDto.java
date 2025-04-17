package mk.ukim.finki.labs.dto.accommodation;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto (String name, Category category, Long hostId, Integer numRooms) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, false);
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms());
    }
}
