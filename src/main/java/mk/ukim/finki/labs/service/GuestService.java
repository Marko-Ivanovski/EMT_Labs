package mk.ukim.finki.labs.service;

import mk.ukim.finki.labs.model.domain.Guest;
import mk.ukim.finki.labs.model.domain.Host;
import mk.ukim.finki.labs.repository.GuestRepository;
import mk.ukim.finki.labs.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public GuestService(GuestRepository guestRepository, HostRepository hostRepository) {
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    public Guest assignHost(Long guestId, Long hostId) {
        Optional<Guest> guestOpt = guestRepository.findById(guestId);
        Optional<Host> hostOpt = hostRepository.findById(hostId);

        if (guestOpt.isPresent() && hostOpt.isPresent()) {
            Guest guest = guestOpt.get();
            Host host = hostOpt.get();

            if (!guest.getHosts().contains(host)) {
                guest.getHosts().add(host);
            }

            if (!host.getGuests().contains(guest)) {
                host.getGuests().add(guest);
            }

            guestRepository.save(guest);
            hostRepository.save(host);

            return guest;
        }
        return null;
    }
}
