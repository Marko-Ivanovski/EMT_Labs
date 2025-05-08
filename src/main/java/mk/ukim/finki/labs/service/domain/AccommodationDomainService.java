package mk.ukim.finki.labs.service.domain;

import org.springframework.stereotype.Service;
import mk.ukim.finki.labs.model.domain.Accommodation;
import mk.ukim.finki.labs.repository.AccommodationRepository;

import java.util.*;

@Service
public class AccommodationDomainService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationDomainService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> findById(Long id) {
         return this.accommodationRepository.findById(id);
    }

    public void save(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void delete(Long id) {
        accommodationRepository.deleteById(id);
    }

    public Optional<Accommodation> update(Long id, Accommodation updatedAccommodation) {
        return accommodationRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedAccommodation.getName());
                    existing.setCategory(updatedAccommodation.getCategory());
                    existing.setHost(updatedAccommodation.getHost());
                    existing.setNumRooms(updatedAccommodation.getNumRooms());
                    return accommodationRepository.save(existing);
                });
    }

    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setRented(true);
                    return accommodationRepository.save(acc);
                });
    }

    public List<Object[]> countAccommodationsByHostRaw() {
        return accommodationRepository.countAccommodationsByHost();
    }
}
