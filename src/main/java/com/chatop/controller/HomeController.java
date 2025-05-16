package com.chatop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chatop.datalayer.service.MessagesService;
import com.chatop.datalayer.service.RentalService;
import com.chatop.datalayer.service.UserService;
import com.chatop.dto.MessagesDto;
import com.chatop.dto.RentalsDto;
import com.chatop.dto.RentalsInputDto;
import com.chatop.dto.RentalsResponseDto;
import com.chatop.datalayer.entity.users;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class HomeController {

    private final RentalService rentalService;
    private final UserService userService;
    private final MessagesService messagesService;

    public HomeController(RentalService rentalService, UserService userService, MessagesService messagesService) {
        this.messagesService = messagesService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @GetMapping("/rentals/{id}")
    public RentalsDto getRentalById(@PathVariable Long id) {
        RentalsDto rental = rentalService.getRentalById(id);
        return rental;
    }
    
    @GetMapping("/rentals")
    public RentalsResponseDto getRentals() {
        List<RentalsDto> rentalsList = rentalService.getAllRentals();
        RentalsResponseDto rentalsResponse = new RentalsResponseDto();
        rentalsResponse.setRentals(rentalsList);
        return rentalsResponse;
}
    
    @PostMapping("/rentals")
    public String postRentals(Authentication auth, @RequestBody RentalsInputDto rental) throws IOException {
        rentalService.createRentals(rental, auth.getName());
        return "Rental Created !";
    }
    
    @PutMapping("/rentals/{rentalId}")
    public String putRentals(Authentication auth, @RequestBody RentalsDto rental, @PathVariable Long rentalId) throws IOException {
        rental.setId(rentalId);
        rentalService.updateRentals(rental, auth.getName());
        return "Rental updated !";
    }
    
    @GetMapping("/user/{id}")
    public users getUser(@PathVariable Long id) {
        users userToReturn = userService.getUserById(id);
        return userToReturn;
    }

    @PostMapping("/messages")
    public String postMessages(Authentication auth, @RequestBody MessagesDto message) {
        messagesService.createMessage(message);
        return "Message Created !";
    }
}
