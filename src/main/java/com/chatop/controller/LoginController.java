package com.chatop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.chatop.datalayer.service.UserService;
import com.chatop.dto.TokenDto;
import com.chatop.services.JWTService;
import com.chatop.dto.UserInputDto;
import com.chatop.dto.UsersOutputDto;


@RestController
@RequestMapping("/auth")
public class LoginController {


    private JWTService jwtService;
    private UserService userService;
    
    public LoginController(JWTService jwtService, UserService userService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    
    @PostMapping("/login")
    public TokenDto loginUser(@RequestBody UserInputDto user) {
        UsersOutputDto userLogin = userService.loginUsers(user);
        TokenDto tokenDto = new TokenDto("");
        if (userLogin == null) {
            tokenDto.setToken("Invalid credentials");
        }else {
            String token = jwtService.generateToken(userLogin.getName());
            tokenDto.setToken(token);
        }
        return tokenDto;
    }

    @PostMapping("/register")
    public TokenDto registerUser(@RequestBody UserInputDto user) {
        UsersOutputDto userRegister = userService.registUsers(user);
        TokenDto tokenDto = new TokenDto("");
        if (userRegister != null) {
            String token = jwtService.generateToken(userRegister.getName());
            tokenDto.setToken(token);
        } else {
            tokenDto.setToken("User already exists");
        }
        return tokenDto;
    }

    @GetMapping("/me")
    public UsersOutputDto getCurrentUser(Authentication authentication) {
        return userService.getUserByName(authentication.getName());
    }
    
}