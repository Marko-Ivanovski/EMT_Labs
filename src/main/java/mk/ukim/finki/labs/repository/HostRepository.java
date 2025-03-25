package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
