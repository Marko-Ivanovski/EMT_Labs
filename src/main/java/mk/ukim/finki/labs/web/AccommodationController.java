package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.accommodation.CreateAccommodationDto;
import mk.ukim.finki.labs.dto.accommodation.DisplayAccommodationDto;
import mk.ukim.finki.labs.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    // GET /api/accommodations
    @Operation(summary = "Get all accommodations", description = "Retrieve a list of all available accommodations.")
    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDto>> findAll() {
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    // GET /api/accommodations/{id}
    @Operation(summary = "Get accommodation by ID", description = "Retrieve accommodation details by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        DisplayAccommodationDto dto = accommodationApplicationService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    // POST /api/accommodations
    @Operation(summary = "Create a new accommodation", description = "Create a new accommodation with provided details.")
    @PostMapping
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto dto) {
        DisplayAccommodationDto saved = accommodationApplicationService.save(dto);
        if (saved == null) {
            return ResponseEntity.badRequest().build(); // e.g. Host not found
        }
        return ResponseEntity.ok(saved);
    }

    // PUT /api/accommodations/{id}
    @Operation(summary = "Update accommodation", description = "Update an existing accommodation details by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id,
                                                          @RequestBody CreateAccommodationDto dto) {
        DisplayAccommodationDto updated = accommodationApplicationService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/accommodations/{id}
    @Operation(summary = "Delete accommodation", description = "Delete an accommodation by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accommodationApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/accommodations/{id}/rent
    @Operation(summary = "Mark accommodation as rented", description = "Mark an accommodation as rented by ID.")
    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id) {
        DisplayAccommodationDto rentedAcc = accommodationApplicationService.markAsRented(id);
        if (rentedAcc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rentedAcc);
    }
}
