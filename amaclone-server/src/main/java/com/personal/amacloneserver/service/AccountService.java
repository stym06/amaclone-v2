package com.personal.amacloneserver.service;

import com.personal.amacloneserver.dto.CustomerAddressDto;
import com.personal.amacloneserver.mapper.CustomerMapper;
import com.personal.amacloneserver.model.Address;
import com.personal.amacloneserver.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final CustomerService customerService;
    private final AddressService addressService;

    @Autowired
    public AccountService(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    public CustomerAddressDto getAccount() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = this.customerService.getCustomerByEmail(email);
        List<Address> addresses = this.addressService.getAddresses(customer);
        return CustomerMapper.toDto(customer, addresses);
    }
}
