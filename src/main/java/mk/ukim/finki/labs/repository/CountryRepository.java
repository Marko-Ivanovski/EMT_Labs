package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
