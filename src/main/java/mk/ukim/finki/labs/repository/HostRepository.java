package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import mk.ukim.finki.labs.projections.HostNameProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    @Query(value = "SELECT country_id, country_name, num_hosts FROM hosts_per_country", nativeQuery = true)
    List<Object[]> countHostsByCountry();

    List<HostNameProjection> findAllProjectedBy();
}
