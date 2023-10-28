package com.personal.amacloneserver.service;

import com.personal.amacloneserver.auth.JwtUtil;
import com.personal.amacloneserver.dto.CustomerLoginDto;
import com.personal.amacloneserver.exception.CustomerAlreadyExistsException;
import com.personal.amacloneserver.exception.InvalidCredentialsException;
import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(CustomerService customerService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> signup(Customer customer) {
        String encodedPassword = this.passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer = this.customerService.createCustomer(customer);
        String token = this.jwtUtil.generateToken(customer.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }

    public Map<String, Object> login(CustomerLoginDto customerLoginDto) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(customerLoginDto.getEmail(), customerLoginDto.getPassword());
            this.authenticationManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(customerLoginDto.getEmail());
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }
}
