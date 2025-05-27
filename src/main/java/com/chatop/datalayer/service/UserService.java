package com.chatop.datalayer.service;

import org.springframework.stereotype.Service;
import com.chatop.datalayer.repository.usersRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.chatop.datalayer.entity.users;
import java.util.Optional;
import com.chatop.dto.UserInputDto;
import com.chatop.dto.UsersOutputDto;

@Service
public class UserService {

    private final usersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

public UserService(usersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UsersOutputDto registUsers(UserInputDto user) {
        // Logic to register a user
        // This is just a placeholder. You would typically save the user to a database here.
        users newUser = new users();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        UsersOutputDto userOutputDto = new UsersOutputDto(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getCreated_at(),
                newUser.getUpdated_at()
        );

        return userOutputDto;
    }
    public UsersOutputDto loginUsers(UserInputDto user) {
        // Logic to login a user
        // This is just a placeholder. You would typically check the user's credentials against a database here.
        Optional<users> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            users existingUser = optionalUser.get();

            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                UsersOutputDto userOutputDto = new UsersOutputDto(
                        existingUser.getId(),
                        existingUser.getName(),
                        existingUser.getEmail(),
                        existingUser.getCreated_at(),
                        existingUser.getUpdated_at()
                );
                return userOutputDto; // Successful login
            } else {
                return null; // Invalid password
            }
        } else {
            return null; // Invalid credentials
        }
    }

    public UsersOutputDto getUserByName(String name) {
        // Logic to get a user by name
        // This is just a placeholder. You would typically retrieve the user from a database here.
        Optional<users> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            users existingUser = optionalUser.get();
            UsersOutputDto userOutputDto = new UsersOutputDto(
                    existingUser.getId(),
                    existingUser.getName(),
                    existingUser.getEmail(),
                    existingUser.getCreated_at(),
                    existingUser.getUpdated_at()
            );
            return userOutputDto;
        } else {
            return null; // User not found
        }
    }

    public UsersOutputDto getUserById(Long id){
        Optional<users> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            users existingUser = optionalUser.get();
            UsersOutputDto userOutputDto = new UsersOutputDto(
                    existingUser.getId(),
                    existingUser.getName(),
                    existingUser.getEmail(),
                    existingUser.getCreated_at(),
                    existingUser.getUpdated_at()
            );
            return userOutputDto;
        }else{
            return null;
        }
    }
}

