package com.chatop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.chatop.datalayer.entity.rentals;
import com.chatop.datalayer.service.RentalService;
import com.chatop.datalayer.service.UserService;
import com.chatop.services.JWTService;
import com.chatop.dto.RentalsDto;
import com.chatop.datalayer.entity.users;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class HomeController {

    private final RentalService rentalService;
    private final UserService userService;
    private final JWTService jwtService;

    public HomeController(RentalService rentalService, JWTService jwtService, UserService userService) {
        this.rentalService = rentalService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/rentals/{id}")
    public RentalsDto getRentalById(@PathVariable Long id) {
        RentalsDto rental = rentalService.getRentalById(id);
        return rental;
    }
    
    @GetMapping("/rentals")
    public List<RentalsDto> getRentals() {
        List<RentalsDto> rentalsList = rentalService.getAllRentals();
        return rentalsList;
    }
    
    @PostMapping("/rentals")
    public String postRentals(Authentication auth, @RequestBody RentalsDto rental) {
        if (rental.getId() != null) {
            return "Rental ID should not be provided for new rentals.";
        }
        rentalService.createRentals(rental, auth.getName());
        return "Rental Created !";
    }
    
    @PutMapping("/rentals")
    public String putRentals(Authentication auth, RentalsDto rental) {
        return "Rental updated !";
    }
    
    @GetMapping("/user/{id}")
    public users getUser(@PathVariable Long id) {
        users userToReturn = userService.getUserById(id);
        return userToReturn;
    }
    
}