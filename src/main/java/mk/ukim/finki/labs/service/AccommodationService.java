package mk.ukim.finki.labs.service;

import org.springframework.stereotype.Service;
import mk.ukim.finki.labs.model.Accommodation;
import mk.ukim.finki.labs.repository.AccommodationRepository;

import java.util.*;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository){
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Accommodation save(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public void delete(Long id) {
        accommodationRepository.deleteById(id);
    }

    public Accommodation update(Long id, Accommodation updatedAccommodation) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        if(optionalAccommodation.isPresent()){
            Accommodation acc = optionalAccommodation.get();
            acc.setName(updatedAccommodation.getName());
            acc.setCategory(updatedAccommodation.getCategory());
            acc.setHost(updatedAccommodation.getHost());
            acc.setNumRooms(updatedAccommodation.getNumRooms());

            return accommodationRepository.save(acc);
        }
        throw new RuntimeException("Accommodation not found with id " + id);
    }

    public Accommodation markAsRented(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        if(optionalAccommodation.isPresent()){
            Accommodation acc = optionalAccommodation.get();
            acc.setRented(true);

            return accommodationRepository.save(acc);
        }
        throw new RuntimeException("Accommodation not found with id " + id);
    }
}
