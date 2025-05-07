package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.reservation.CreateReservationDto;
import mk.ukim.finki.labs.dto.reservation.DisplayReservationDto;
import mk.ukim.finki.labs.service.application.TemporaryReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class TemporaryReservationController {

    private final TemporaryReservationApplicationService reservationService;

    public TemporaryReservationController(TemporaryReservationApplicationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Get all temporary reservations for the logged-in user")
    @GetMapping
    public ResponseEntity<List<DisplayReservationDto>> getReservations(Authentication auth) {
        return ResponseEntity.ok(reservationService.getUserReservations(auth.getName()));
    }

    @Operation(summary = "Add an accommodation to the reservation list")
    @PostMapping
    public ResponseEntity<String> addToReservation(Authentication auth, @RequestBody CreateReservationDto dto) {
        return reservationService.addToReservation(auth.getName(), dto)
                .map(error -> ResponseEntity.badRequest().body(error))
                .orElse(ResponseEntity.ok("Accommodation added to reservation list"));
    }

    @Operation(summary = "Remove a temporary reservation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        reservationService.removeReservation(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Confirm all reservations (mark as rented)")
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmAll(Authentication auth) {
        reservationService.confirmAll(auth.getName());
        return ResponseEntity.ok("All accommodations reserved successfully.");
    }
}
