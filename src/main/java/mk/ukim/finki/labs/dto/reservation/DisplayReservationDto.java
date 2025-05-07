package mk.ukim.finki.labs.dto.reservation;

import mk.ukim.finki.labs.model.domain.TemporaryReservation;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayReservationDto(Long reservationId, String accommodationName, boolean rented) {

    public static DisplayReservationDto from(TemporaryReservation tr) {
        return new DisplayReservationDto(
                tr.getId(),
                tr.getAccommodation().getName(),
                tr.getAccommodation().isRented()
        );
    }

    public static List<DisplayReservationDto> from(List<TemporaryReservation> reservations) {
        return reservations.stream().map(DisplayReservationDto::from).collect(Collectors.toList());
    }
}
