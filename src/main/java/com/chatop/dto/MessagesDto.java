package com.chatop.dto;


public class MessagesDto {
    private String message;
    private Long rental_id;
    private Long user_id;

    public MessagesDto() {
    }
    
    public MessagesDto(String message, Long user_id, Long rental_id) {
        this.message = message;
        this.rental_id = rental_id;
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getRental_id() {
        return rental_id;
    }
    public void setRental_id(Long rental_id) {
        this.rental_id = rental_id;
    }

    
}
