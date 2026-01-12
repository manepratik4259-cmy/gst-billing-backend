package com.fullstack.controller;

import com.fullstack.dto.LogInRequest;
import com.fullstack.entity.User;
import com.fullstack.service.CustomUserService;
import com.fullstack.service.GSTBillService;
import com.fullstack.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final JWTUtil jwtUtil;

    private final CustomUserService customUserService;

    private final AuthenticationManager authenticationManager;

    private final GSTBillService gstBillService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {

        return new ResponseEntity<>(gstBillService.signUp(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> generateToken(@RequestBody LogInRequest logInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.userName(), logInRequest.userPassword()));
        return new ResponseEntity<>(jwtUtil.generateToken(logInRequest.userName()), HttpStatus.OK);
    }
}
