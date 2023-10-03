package com.coast.brenno.bikestoreback.controller;

import com.coast.brenno.bikestoreback.dto.request.user.LoginRequest;
import com.coast.brenno.bikestoreback.dto.request.user.SignUpReq;
import com.coast.brenno.bikestoreback.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Sign In
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.signIn(loginRequest);
    }

    /**
     * Sign Up
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpReq signUpRequest) {
        return userService.signUp(signUpRequest);
    }
}