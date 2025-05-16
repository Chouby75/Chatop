package com.chatop.dto;

import java.util.List;

public class RentalsResponseDto {
    List<RentalsDto> rentals;

    public List<RentalsDto> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalsDto> rentals) {
        this.rentals = rentals;
    }

    
}
