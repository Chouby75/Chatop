package com.chatop.datalayer.service;

import org.springframework.stereotype.Service;
import com.chatop.datalayer.repository.rentalsRepository;
import com.chatop.datalayer.repository.usersRepository;
import com.chatop.dto.RentalsDto;
import com.chatop.datalayer.entity.rentals;
import com.chatop.datalayer.entity.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentalService {

    private final rentalsRepository rentalsRepository;
    private final usersRepository usersRepository;
    
    public RentalService(rentalsRepository rentalsRepository, usersRepository usersRepository) {
        this.rentalsRepository = rentalsRepository;
        this.usersRepository = usersRepository;
    }

    public void createRentals(RentalsDto rental, String username){
        // Logic to create a rental
        // This is just a placeholder. You would typically save the rental to a database here.
        Optional<users> user = usersRepository.findByName(username);
        users existingUser = user.get();

        rentals newRental = new rentals();
        newRental.setName(rental.getName());
        newRental.setDescription(rental.getDescription());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setOwnerId(existingUser);

        // Save the rental to the database
        rentalsRepository.save(newRental);

        return;
    }

    public RentalsDto getRentalById(Long id) {
        // Logic to get a rental by name
        // This is just a placeholder. You would typically retrieve the rental from a database here.
        Optional<rentals> optionalRental = rentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            rentals existingRental = optionalRental.get();
            RentalsDto rental = convertToDto(existingRental);
            return rental;
        } else {
            return null; // Rental not found
        }
    }

    public List<RentalsDto> getAllRentals() {
        Iterable<rentals> rentalsEntity = rentalsRepository.findAll();
        List<RentalsDto> allRentals = StreamSupport
                                        .stream(rentalsEntity.spliterator(), false)
                                        .map(this::convertToDto)
                                        .collect(Collectors.toList());
        return allRentals;
    }

    private RentalsDto convertToDto(rentals rental) {
        if (rental == null) {
            return null;
        }
        RentalsDto dto = new RentalsDto();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());

        // Gérer owner_id
        if (rental.getOwnerId() != null) {
            // Supposons que votre entité 'users' a une méthode getId()
            dto.setOwner_id(rental.getOwnerId().getId());
        } else {
            dto.setOwner_id(null); // Ou une valeur par défaut si applicable
        }

        dto.setCreated_at(rental.getCreatedAt());
        dto.setUpdated_at(rental.getUpdatedAt());
        return dto;
    }
}
