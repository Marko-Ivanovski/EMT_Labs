package mk.ukim.finki.labs.service.domain;

import mk.ukim.finki.labs.model.domain.Country;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostDomainService {

    private final HostRepository hostRepository;

    public HostDomainService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    public void save(Host host) {
        hostRepository.save(host);
    }

    public void delete(Long id) {
        hostRepository.deleteById(id);
    }

    public Optional<Host> update(Long id, Host updatedHost) {
        return hostRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedHost.getName());
                    existing.setSurname(updatedHost.getSurname());
                    existing.setCountry(updatedHost.getCountry());
                    return hostRepository.save(existing);
                });
    }
}
