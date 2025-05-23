package com.chatop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatop.dto.MessagesDto;
import com.chatop.dto.ResponseMessageDto;
import com.chatop.dto.UsersOutputDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.datalayer.service.MessagesService;
import com.chatop.datalayer.service.UserService;

@RestController
public class MessagesController {

    private final MessagesService messagesService;
    private final UserService userService;

    public MessagesController(MessagesService messagesService, UserService userService) {
        this.userService = userService;
        this.messagesService = messagesService;
    }

    @PostMapping("/messages")
    public ResponseMessageDto postMessages(@RequestBody MessagesDto message) {
        System.out.println("Message: " + message.getMessage());
        System.out.println("Rental ID: " + message.getRental_id());
        System.out.println("User ID: " + message.getUser_id());
        System.out.println("DTO" + message);
        messagesService.createMessage(message);
        ResponseMessageDto responseMessage = new ResponseMessageDto();
        responseMessage.setMessage("Message send with success");
        return responseMessage;
    }

    @GetMapping("/user/{id}")
    public UsersOutputDto getUserById(@PathVariable Long id) {
        UsersOutputDto user = userService.getUserById(id);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
    
}
