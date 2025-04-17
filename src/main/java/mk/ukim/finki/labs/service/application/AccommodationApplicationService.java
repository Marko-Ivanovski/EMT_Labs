package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.service.domain.HostDomainService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.labs.service.domain.AccommodationDomainService;
import mk.ukim.finki.labs.dto.accommodation.CreateAccommodationDto;
import mk.ukim.finki.labs.dto.accommodation.DisplayAccommodationDto;

import java.util.List;

@Service
public class AccommodationApplicationService {
    private final AccommodationDomainService accommodationDomainService;
    private final HostDomainService hostDomainService;

    public AccommodationApplicationService(AccommodationDomainService accommodationDomainService, HostDomainService hostDomainService) {
        this.accommodationDomainService = accommodationDomainService;
        this.hostDomainService = hostDomainService;
    }

    public List<DisplayAccommodationDto> findAll() {
        return accommodationDomainService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    public DisplayAccommodationDto findById(Long id) {
        return accommodationDomainService.findById(id).map(DisplayAccommodationDto::from).orElse(null);
    }

    public DisplayAccommodationDto save(CreateAccommodationDto createAccommodationDto) {
        Host host = hostDomainService.findById(createAccommodationDto.hostId());

        if (host == null) {
            return null;
        } else {
            Accommodation accommodation = createAccommodationDto.toAccommodation(host);
            accommodationDomainService.save(accommodation);
            return DisplayAccommodationDto.from(accommodation);
        }
    }

    public void delete(Long id) {
        accommodationDomainService.delete(id);
    }

    public DisplayAccommodationDto update(Long id, CreateAccommodationDto createAccommodationDto) {
        Host host = hostDomainService.findById(createAccommodationDto.hostId());
        return accommodationDomainService.update(id, createAccommodationDto.toAccommodation(host)).map(DisplayAccommodationDto::from).orElse(null);
    }

    public DisplayAccommodationDto markAsRented(Long id) {
        return accommodationDomainService.markAsRented(id).map(DisplayAccommodationDto::from).orElse(null);
    }
}
