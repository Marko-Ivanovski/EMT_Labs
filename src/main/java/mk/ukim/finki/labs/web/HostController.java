package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.host.CreateHostDto;
import mk.ukim.finki.labs.dto.host.DisplayHostDto;
import mk.ukim.finki.labs.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieve a list of all hosts.")
    @GetMapping
    public ResponseEntity<List<DisplayHostDto>> findAll() {
        return ResponseEntity.ok(hostApplicationService.findAll());
    }

    @Operation(summary = "Get host by ID", description = "Retrieve a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new host", description = "Create a new host with a given name, surname, and country.")
    @PostMapping
    public ResponseEntity<DisplayHostDto> create(@RequestBody CreateHostDto dto) {
        return hostApplicationService.create(dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build()); // Country might not exist
    }

    @Operation(summary = "Update a host", description = "Update an existing host's data.")
    @PutMapping("/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto dto) {
        return hostApplicationService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Delete a host by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
