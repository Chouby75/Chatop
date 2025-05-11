package com.chatop.datalayer.service;
import org.springframework.stereotype.Service;
import com.chatop.datalayer.repository.messagesRepository;
import com.chatop.datalayer.entity.messages;
import com.chatop.dto.MessagesDto;

@Service
public class MessagesService {
    private final messagesRepository messagesRepository;

    public MessagesService(messagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void createMessage(MessagesDto message) {
        // Logic to create a message
        // This is just a placeholder. You would typically save the message to a database here.
        messages newMessage = new messages();
        newMessage.setMessage(message.getMessage());
        newMessage.setUser_id(message.getUser_id());
        newMessage.setRental_id(message.getRental_id());

        // Save the message to the database
        messagesRepository.save(newMessage);
        return;
    }
}