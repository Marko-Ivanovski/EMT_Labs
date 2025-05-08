package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    @Query(value = "SELECT host_id, host_name, num_accommodations FROM accommodations_per_host", nativeQuery = true)
    List<Object[]> countAccommodationsByHost();
}
