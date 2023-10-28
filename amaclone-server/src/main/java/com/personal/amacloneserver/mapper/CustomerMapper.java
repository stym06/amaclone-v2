package com.personal.amacloneserver.mapper;

import com.personal.amacloneserver.dto.AddressDto;
import com.personal.amacloneserver.dto.CustomerAddressDto;
import com.personal.amacloneserver.dto.CustomerSignupDto;
import com.personal.amacloneserver.model.Address;
import com.personal.amacloneserver.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerAddressDto toDto(Customer customer, List<Address> addresses) {
        CustomerAddressDto customerAddressDto = new CustomerAddressDto();
        customerAddressDto.setCustomerId(customer.getId());
        customerAddressDto.setCustomerName(customer.getName());
        customerAddressDto.setCustomerEmail(customer.getEmail());
        List<AddressDto> addressDtoList = new ArrayList<>();
        addresses.forEach(address -> {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(address.getId());
            addressDto.setName(address.getName());
            addressDto.setPhoneNumber(address.getPhoneNumber());
            addressDto.setAddressLineA(address.getAddressLineA());
            addressDto.setAddressLineB(address.getAddressLineB());
            addressDto.setPincode(address.getPincode());
            addressDto.setActive(address.isActive());
            addressDtoList.add(addressDto);
        });
        customerAddressDto.setAddresses(addressDtoList);
        return customerAddressDto;
    }
}

