package com.personal.amacloneserver.service;

import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = this.customerRepository.findByEmail(email);
        if(customer.isEmpty()) {
            throw new UsernameNotFoundException("Could not find customer with email: " + email);
        }
        return new User(email, customer.get().getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
    }
}
