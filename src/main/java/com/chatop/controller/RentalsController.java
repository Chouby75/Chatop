package com.chatop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import com.chatop.datalayer.service.RentalService;
import com.chatop.dto.RentalsDto;
import com.chatop.dto.RentalsInputDto;
import com.chatop.dto.RentalsResponseDto;
import com.chatop.dto.ResponseMessageDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RentalsController {

    private final RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
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
    public ResponseMessageDto postRentals(Authentication auth, @RequestBody RentalsInputDto rental) throws IOException {
        rentalService.createRentals(rental, auth.getName());
        ResponseMessageDto responseMessage = new ResponseMessageDto();
        responseMessage.setMessage("Rental Created !");
        
        return responseMessage;
    }
    
    @PutMapping("/rentals/{rentalId}")
    public ResponseMessageDto putRentals(Authentication auth, @RequestBody RentalsDto rental, @PathVariable Long rentalId) throws IOException {
        rental.setId(rentalId);
        rentalService.updateRentals(rental, auth.getName());
        ResponseMessageDto responseMessage = new ResponseMessageDto();
        responseMessage.setMessage("Rental updated !");
        return responseMessage;
    }
}
