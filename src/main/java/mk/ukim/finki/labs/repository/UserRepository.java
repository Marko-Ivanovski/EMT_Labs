package mk.ukim.finki.labs.repository;

import mk.ukim.finki.labs.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @EntityGraph(value = "User.withoutReservations", type = EntityGraph.EntityGraphType.FETCH)
    List<User> findAll();
}
