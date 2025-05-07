package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.reservation.CreateReservationDto;
import mk.ukim.finki.labs.dto.reservation.DisplayReservationDto;
import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.TemporaryReservation;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.service.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationService {

    private final TemporaryReservationDomainService reservationDomainService;
    private final AccommodationDomainService accommodationDomainService;
    private final UserDomainService userDomainService;

    public TemporaryReservationApplicationService(TemporaryReservationDomainService reservationDomainService, AccommodationDomainService accommodationDomainService, UserDomainService userDomainService) {
        this.reservationDomainService = reservationDomainService;
        this.accommodationDomainService = accommodationDomainService;
        this.userDomainService = userDomainService;
    }

    public Optional<String> addToReservation(String username, CreateReservationDto dto) {
        Optional<User> userOpt = userDomainService.findByUsername(username);
        Optional<Accommodation> accOpt = accommodationDomainService.findById(dto.accommodationId());

        if (userOpt.isEmpty() || accOpt.isEmpty()) return Optional.of("User or Accommodation not found");

        User user = userOpt.get();
        Accommodation acc = accOpt.get();

        if (acc.isRented()) return Optional.of("Accommodation is already rented");

        if (reservationDomainService.exists(user, acc)) return Optional.of("Accommodation is already in your reservation list");

        TemporaryReservation tr = new TemporaryReservation(user, acc);
        reservationDomainService.save(tr);

        return Optional.empty(); // success
    }

    public List<DisplayReservationDto> getUserReservations(String username) {
        return userDomainService.findByUsername(username)
                .map(reservationDomainService::findAllByUser)
                .map(DisplayReservationDto::from)
                .orElse(List.of());
    }

    public void removeReservation(Long id) {
        reservationDomainService.delete(id);
    }

    public void confirmAll(String username) {
        Optional<User> userOpt = userDomainService.findByUsername(username);
        if (userOpt.isEmpty()) return;

        List<TemporaryReservation> reservations = reservationDomainService.findAllByUser(userOpt.get());

        for (TemporaryReservation tr : reservations) {
            Accommodation acc = tr.getAccommodation();
            acc.setRented(true);
            accommodationDomainService.save(acc);
        }

        reservationDomainService.deleteAllByUser(userOpt.get());
    }
}
