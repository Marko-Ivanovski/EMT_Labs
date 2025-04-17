package mk.ukim.finki.labs.dto.accommodation;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto (Long id, String name, Category category, String hostFirstName, String hostLastName, Integer numRooms, boolean rented) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(accommodation.getId(), accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getName(), accommodation.getHost().getSurname(), accommodation.getNumRooms(), accommodation.isRented());
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
}
