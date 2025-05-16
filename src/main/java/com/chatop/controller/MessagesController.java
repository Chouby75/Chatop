package com.chatop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatop.dto.MessagesDto;
import com.chatop.dto.ResponseMessageDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.datalayer.service.MessagesService;

@RestController
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/messages")
    public ResponseMessageDto postMessages(Authentication auth, @RequestBody MessagesDto message) {
        messagesService.createMessage(message);
        ResponseMessageDto responseMessage = new ResponseMessageDto();
        responseMessage.setMessage("Message Created !");
        return responseMessage;
    }
    
}
