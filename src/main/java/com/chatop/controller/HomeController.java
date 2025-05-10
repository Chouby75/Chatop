package com.chatop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.chatop.datalayer.entity.rentals;
import com.chatop.datalayer.service.RentalService;
import com.chatop.services.JWTService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class HomeController {

    private final RentalService rentalService;
    private final JWTService jwtService;

    public HomeController(RentalService rentalService, JWTService jwtService) {
        this.rentalService = rentalService;
        this.jwtService = jwtService;
    }

    @GetMapping("/rentals/{id}")
    public String getRentalById(@PathVariable String id) {
        return "Rental ID: " + id;
    }
    
    @GetMapping("/rentals")
    public Iterable<rentals> getRentals(Authentication auth) {
        Iterable<rentals> rentalsList = rentalService.getAllRentals();
        return rentalsList;
    }
    
    @PostMapping("/rentals")
    public String postRentals(Authentication auth, @RequestBody rentals rental) {
        if (rental.getId() != null) {
            return "Rental ID should not be provided for new rentals.";
        }
        rentalService.createRentals(rental, auth.getName());
        return "Rental Created !";
    }
    
    @PutMapping("/rentals")
    public String putRentals(Authentication auth, rentals rental) {
        return "Rental updated !";
    }
    
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id) {
        return "here the user !";
    }
    
}