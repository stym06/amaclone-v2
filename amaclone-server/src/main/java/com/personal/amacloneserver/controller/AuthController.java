package com.personal.amacloneserver.controller;

import com.personal.amacloneserver.dto.CustomerLoginDto;
import com.personal.amacloneserver.dto.CustomerSignupDto;
import com.personal.amacloneserver.mapper.CustomerMapper;
import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.signup(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody CustomerLoginDto customerLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.login(customerLoginDto));
    }
}
