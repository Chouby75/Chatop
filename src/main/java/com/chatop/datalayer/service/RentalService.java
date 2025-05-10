package com.chatop.datalayer.service;

import org.springframework.stereotype.Service;
import com.chatop.datalayer.repository.rentalsRepository;
import com.chatop.datalayer.repository.usersRepository;
import com.chatop.datalayer.entity.rentals;
import com.chatop.datalayer.entity.users;
import java.util.Optional;

@Service
public class RentalService {

    private final rentalsRepository rentalsRepository;
    private final usersRepository usersRepository;
    
    public RentalService(rentalsRepository rentalsRepository, usersRepository usersRepository) {
        this.rentalsRepository = rentalsRepository;
        this.usersRepository = usersRepository;
    }

    public rentals createRentals(rentals rental, String username){
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

        return newRental;
    }

    public rentals getRentalByName(String name) {
        // Logic to get a rental by name
        // This is just a placeholder. You would typically retrieve the rental from a database here.
        Optional<rentals> optionalRental = rentalsRepository.findByName(name);
        if (optionalRental.isPresent()) {
            rentals existingRental = optionalRental.get();
            return existingRental;
        } else {
            return null; // Rental not found
        }
    }

    public Iterable<rentals> getAllRentals() {
        // Logic to get rentals by user
        // This is just a placeholder. You would typically retrieve the rentals from a database here
        Iterable<rentals> allRentals = rentalsRepository.findAll();
        System.out.println("All Rentals: " + allRentals);
        return allRentals;
    }
}
