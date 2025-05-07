package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.country.CreateCountryDto;
import mk.ukim.finki.labs.dto.country.DisplayCountryDto;
import mk.ukim.finki.labs.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieve a list of all countries.")
    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        return ResponseEntity.ok(countryApplicationService.findAll());
    }

    @Operation(summary = "Get country by ID", description = "Retrieve a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new country", description = "Create a new country with name and continent.")
    @PostMapping
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto dto) {
        DisplayCountryDto saved = countryApplicationService.create(dto);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Update a country", description = "Update an existing country's data.")
    @PutMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto dto) {
        return countryApplicationService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Delete a country by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
