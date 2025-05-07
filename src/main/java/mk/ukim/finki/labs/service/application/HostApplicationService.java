package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.host.CountHostDto;
import mk.ukim.finki.labs.dto.host.CreateHostDto;
import mk.ukim.finki.labs.dto.host.DisplayHostDto;
import mk.ukim.finki.labs.model.domain.Country;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.projections.HostNameProjection;
import mk.ukim.finki.labs.service.domain.CountryDomainService;
import mk.ukim.finki.labs.service.domain.HostDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationService {

    private final HostDomainService hostDomainService;
    private final CountryDomainService countryDomainService;

    public HostApplicationService(HostDomainService hostDomainService, CountryDomainService countryDomainService) {
        this.hostDomainService = hostDomainService;
        this.countryDomainService = countryDomainService;
    }

    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostDomainService.findAll());
    }

    public Optional<DisplayHostDto> findById(Long id) {
        return hostDomainService.findById(id)
                .map(DisplayHostDto::from);
    }

    public Optional<DisplayHostDto> create(CreateHostDto dto) {
        Optional<Country> countryOpt = countryDomainService.findById(dto.countryId());
        if (countryOpt.isEmpty()) return Optional.empty();

        Host host = dto.toHost(countryOpt.get());
        hostDomainService.save(host);

        return Optional.of(DisplayHostDto.from(host));
    }

    public Optional<DisplayHostDto> update(Long id, CreateHostDto dto) {
        Optional<Country> countryOpt = countryDomainService.findById(dto.countryId());
        if (countryOpt.isEmpty()) return Optional.empty();

        Host updated = dto.toHost(countryOpt.get());
        return hostDomainService.update(id, updated)
                .map(DisplayHostDto::from);
    }

    public void delete(Long id) {
        hostDomainService.delete(id);
    }

    public List<CountHostDto> countHostsByCountry() {
        return hostDomainService.countHostsByCountryRaw().stream()
                .map(row -> new CountHostDto(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).longValue()
                )).toList();
    }

    public List<HostNameProjection> getAllHostNames() {
        return hostDomainService.findAllHostNames();
    }
}
