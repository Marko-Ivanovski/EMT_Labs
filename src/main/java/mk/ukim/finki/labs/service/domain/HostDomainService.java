package mk.ukim.finki.labs.service.domain;

import mk.ukim.finki.labs.events.HostCreatedEvent;
import mk.ukim.finki.labs.events.HostDeletedEvent;
import mk.ukim.finki.labs.events.HostUpdatedEvent;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.projections.HostNameProjection;
import mk.ukim.finki.labs.repository.HostRepository;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;


import java.util.List;
import java.util.Optional;

@Service
public class HostDomainService {

    private final HostRepository hostRepository;
    private final ApplicationEventPublisher eventPublisher;

    public HostDomainService(HostRepository hostRepository, ApplicationEventPublisher eventPublisher) {
        this.hostRepository = hostRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    public void save(Host host) {
        hostRepository.save(host);
        eventPublisher.publishEvent(new HostCreatedEvent(host.getId()));
    }

    public void delete(Long id) {
        hostRepository.deleteById(id);
        eventPublisher.publishEvent(new HostDeletedEvent(id));
    }

    public Optional<Host> update(Long id, Host updatedHost) {
        return hostRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedHost.getName());
                    existing.setSurname(updatedHost.getSurname());
                    existing.setCountry(updatedHost.getCountry());

                    Host saved = hostRepository.save(existing);
                    eventPublisher.publishEvent(new HostUpdatedEvent(saved.getId()));

                    return saved;
                });
    }

    public List<Object[]> countHostsByCountryRaw() {
        return hostRepository.countHostsByCountry();
    }

    public List<HostNameProjection> findAllHostNames() {
        return hostRepository.findAllProjectedBy();
    }
}
