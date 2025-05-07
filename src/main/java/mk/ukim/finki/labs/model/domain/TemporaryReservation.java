package mk.ukim.finki.labs.model.domain;

import jakarta.persistence.*;

@Entity
public class TemporaryReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Accommodation accommodation;

    public TemporaryReservation() {}

    public TemporaryReservation(User user, Accommodation accommodation) {
        this.user = user;
        this.accommodation = accommodation;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
