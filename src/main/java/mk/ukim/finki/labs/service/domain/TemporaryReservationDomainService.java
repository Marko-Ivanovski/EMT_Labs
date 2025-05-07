package mk.ukim.finki.labs.service.domain;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.TemporaryReservation;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.repository.TemporaryReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporaryReservationDomainService {

    private final TemporaryReservationRepository reservationRepository;

    public TemporaryReservationDomainService(TemporaryReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<TemporaryReservation> findAllByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    public boolean exists(User user, Accommodation accommodation) {
        return reservationRepository.existsByUserAndAccommodation(user, accommodation);
    }

    public void save(TemporaryReservation reservation) {
        reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public void deleteAllByUser(User user) {
        reservationRepository.deleteAllByUser(user);
    }
}
