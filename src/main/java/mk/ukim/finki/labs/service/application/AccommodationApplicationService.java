package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.accommodation.CountAccommodationDto;
import mk.ukim.finki.labs.dto.accommodation.CreateAccommodationDto;
import mk.ukim.finki.labs.dto.accommodation.DisplayAccommodationDto;
import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.service.domain.AccommodationDomainService;
import mk.ukim.finki.labs.service.domain.HostDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationService {

    private final AccommodationDomainService accommodationDomainService;
    private final HostDomainService hostDomainService;

    public AccommodationApplicationService(AccommodationDomainService accommodationDomainService, HostDomainService hostDomainService) {
        this.accommodationDomainService = accommodationDomainService;
        this.hostDomainService = hostDomainService;
    }

    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationDomainService.findAll());
    }

    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationDomainService.findById(id)
                .map(DisplayAccommodationDto::from);
    }

    public Optional<DisplayAccommodationDto> create(CreateAccommodationDto dto) {
        Optional<Host> hostOpt = hostDomainService.findById(dto.hostId());
        if (hostOpt.isEmpty()) return Optional.empty();

        Accommodation accommodation = dto.toAccommodation(hostOpt.get());
        accommodationDomainService.save(accommodation);

        return Optional.of(DisplayAccommodationDto.from(accommodation));
    }

    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Optional<Host> hostOpt = hostDomainService.findById(dto.hostId());
        if (hostOpt.isEmpty()) return Optional.empty();

        Accommodation updated = dto.toAccommodation(hostOpt.get());
        return accommodationDomainService.update(id, updated)
                .map(DisplayAccommodationDto::from);
    }

    public void delete(Long id) {
        accommodationDomainService.delete(id);
    }

    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationDomainService.markAsRented(id)
                .map(DisplayAccommodationDto::from);
    }

    public List<CountAccommodationDto> countAccommodationsByHost() {
        return accommodationDomainService.countAccommodationsByHostRaw().stream()
                .map(row -> new CountAccommodationDto(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).longValue()
                )).toList();
    }
}
