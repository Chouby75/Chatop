package com.chatop.dto;


public class MessagesDto {
    private String message;
    private int rental_id;
    private int user_id;

    public MessagesDto() {
    }
    
    public MessagesDto(String message, int user_id, int rental_id) {
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
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getRental_id() {
        return rental_id;
    }
    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    
}