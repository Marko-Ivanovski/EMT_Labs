package mk.ukim.finki.labs.web;

import mk.ukim.finki.labs.model.domain.Guest;
import mk.ukim.finki.labs.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    // GET api/guests
    @GetMapping
    public ResponseEntity<List<Guest>> findAll() {
        return ResponseEntity.ok(guestService.findAll());
    }

    // POST api/guests
    @PostMapping
    public ResponseEntity<Guest> save(@RequestBody Guest guest) {
        Guest saved = guestService.save(guest);
        return ResponseEntity.ok(saved);
    }

    // DELETE api/guests/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // POST api/guests/{guestId}/assign-host/{hostId}
    @PostMapping("/{guestId}/assign-host/{hostId}")
    public ResponseEntity<Guest> assignHost(@PathVariable Long guestId, @PathVariable Long hostId) {
        Guest updatedGuest = guestService.assignHost(guestId, hostId);
        if (updatedGuest != null) {
            return ResponseEntity.ok(updatedGuest);
        }
        return ResponseEntity.notFound().build();
    }
}
