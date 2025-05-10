package com.chatop.datalayer.service;

import org.springframework.stereotype.Service;
import com.chatop.datalayer.repository.usersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.chatop.datalayer.entity.users;
import java.util.Optional;

@Service
public class UserService {

    private final usersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

public UserService(usersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public users registUsers(users user) {
        // Logic to register a user
        // This is just a placeholder. You would typically save the user to a database here.
        users newUser = new users();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        // newUser.setCreated_at(new Date());
        // newUser.setUpdated_at(new Date());
        // Save the user to the database
        userRepository.save(newUser);

        return newUser;
    }
    public users loginUsers(users user) {
        // Logic to login a user
        // This is just a placeholder. You would typically check the user's credentials against a database here.
        Optional<users> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            users existingUser = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                return existingUser; // Successful login
            } else {
                return null; // Invalid password
            }
        } else {
            return null; // Invalid credentials
        }
    }

    public users getUserByName(String name) {
        // Logic to get a user by name
        // This is just a placeholder. You would typically retrieve the user from a database here.
        Optional<users> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            users existingUser = optionalUser.get();
            return existingUser;
        } else {
            return null; // User not found
        }
    }

    public users getUserById(Long id){
        Optional<users> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            users existingUser = optionalUser.get();
            return existingUser;
        }else{
            return null;
        }
    }
}

