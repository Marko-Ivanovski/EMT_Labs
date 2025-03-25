package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
