package com.personal.amacloneserver.controller;

import com.personal.amacloneserver.dto.AddressDto;
import com.personal.amacloneserver.dto.CustomerAddressDto;
import com.personal.amacloneserver.model.Address;
import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.service.AddressService;
import com.personal.amacloneserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;

    @Autowired
    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @PutMapping("/addresses")
    ResponseEntity<CustomerAddressDto> createAddressForCustomer(@RequestBody AddressDto addressDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.createAddressForCustomer(addressDto));
    }

    @PutMapping("/addresses/{id}")
    ResponseEntity<AddressDto> updateAddress(@PathVariable("id") Long addressId, @RequestBody AddressDto addressDto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = this.customerService.getCustomerByEmail(email);
        Address address = new Address(customer, addressDto);
        address = this.addressService.updateAddress(addressId, address);
        return ResponseEntity.status(HttpStatus.OK).body(new AddressDto(address));
    }
}
