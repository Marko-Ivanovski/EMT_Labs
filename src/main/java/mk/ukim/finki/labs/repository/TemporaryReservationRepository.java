package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.TemporaryReservation;
import mk.ukim.finki.labs.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findAllByUser(User user);
    boolean existsByUserAndAccommodation(User user, Accommodation accommodation);
    void deleteAllByUser(User user);
}
