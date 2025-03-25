package mk.ukim.finki.labs.web;

import mk.ukim.finki.labs.model.Accommodation;
import mk.ukim.finki.labs.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    // GET /api/accommodations
    @GetMapping
    public ResponseEntity<List<Accommodation>> findAll() {
        return ResponseEntity.ok(accommodationService.findAll());
    }

    // POST /api/accommodations
    @PostMapping
    public ResponseEntity<Accommodation> save(@RequestBody Accommodation accommodation) {
        Accommodation saved = accommodationService.save(accommodation);
        return ResponseEntity.ok(saved);
    }

    // DELETE /api/accommodations/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accommodationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/accommodations/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id,
                                                @RequestBody Accommodation accommodation) {
        Accommodation updated = accommodationService.update(id, accommodation);
        return ResponseEntity.ok(updated);
    }

    // PUT /api/accommodations/{id}/rent
    @PutMapping("/rent/{id}")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long id) {
        Accommodation rentedAcc = accommodationService.markAsRented(id);
        return ResponseEntity.ok(rentedAcc);
    }
}