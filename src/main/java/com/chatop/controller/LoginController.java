package com.chatop.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.chatop.datalayer.entity.users;
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
        if (userLogin == null) {
            TokenDto tokenDto = new TokenDto("Invalid credentials");
            return tokenDto;
        }else {
            String token = jwtService.generateToken(userLogin.getName());
            TokenDto tokenDto = new TokenDto(token);
            return tokenDto;
        }
    }

    @PostMapping("/register")
    public TokenDto registerUser(@RequestBody UserInputDto user) {
        UsersOutputDto userRegister = userService.registUsers(user);
        if (userRegister != null) {
            String token = jwtService.generateToken(userRegister.getName());
            TokenDto tokenDto = new TokenDto(token);
            return tokenDto;
        } else {
            TokenDto tokenDto = new TokenDto("Invalid credentials");
            return tokenDto;
        }
    }

    @GetMapping("/me")
    public UsersOutputDto getCurrentUser(Authentication authentication) {
        UsersOutputDto userMe = userService.getUserByName(authentication.getName());
        if (userMe == null) {
            return userMe;
        }else {
            return userMe;
        }
    }
    
}