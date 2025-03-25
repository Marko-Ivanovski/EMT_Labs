package mk.ukim.finki.labs.model;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.labs.model.enumerations.Category;

@Data
@Entity
@NoArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;

    private boolean rented = false;

    public Accommodation(String name, Category category, Host host, Integer numRooms, boolean rented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = rented;
    }
}
