package com.personal.amacloneserver.service;

import com.personal.amacloneserver.dto.AddressDto;
import com.personal.amacloneserver.dto.CustomerAddressDto;
import com.personal.amacloneserver.exception.CustomerAlreadyExistsException;
import com.personal.amacloneserver.exception.CustomerNotFoundException;
import com.personal.amacloneserver.mapper.CustomerMapper;
import com.personal.amacloneserver.model.Address;
import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public Customer getCustomer(Long customerId) {
        return this.customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer createCustomer(Customer customer) {
        Optional<Customer> existingCustomer = this.customerRepository.findByEmail(customer.getEmail());
        if(existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException();
        }
        return this.customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = this.customerRepository.findByEmail(email);
        if(customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return customer.get();
    }

    public CustomerAddressDto createAddressForCustomer(AddressDto addressDto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = getCustomerByEmail(email);
        Address address = new Address(customer, addressDto);
        address = this.addressService.createAddress(address);
        return CustomerMapper.toDto(customer, Collections.singletonList(address));
    }
}
