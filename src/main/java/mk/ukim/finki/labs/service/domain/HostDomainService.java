package mk.ukim.finki.labs.service.domain;

import org.springframework.stereotype.Service;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.repository.HostRepository;

import java.util.List;

@Service
public class HostDomainService {
    private final HostRepository hostRepository;

    public HostDomainService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    public Host findById(Long id) {
        return hostRepository.findById(id).orElseThrow(() -> new RuntimeException("Host not found with id " + id));
    }
}
