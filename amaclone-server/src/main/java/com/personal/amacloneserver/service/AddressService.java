package com.personal.amacloneserver.service;

import com.personal.amacloneserver.dto.AddressDto;
import com.personal.amacloneserver.exception.AddressNotFoundException;
import com.personal.amacloneserver.model.Address;
import com.personal.amacloneserver.model.Customer;
import com.personal.amacloneserver.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses(Customer customer) {
        return this.addressRepository.findByCustomer_Id(customer.getId());
    }

    public Address createAddress(Address address) {
        return this.addressRepository.save(address);
    }

    public Address updateAddress(Long addressId, Address address) {
        Optional<Address> foundAddress = this.addressRepository.findById(addressId);
        if(foundAddress.isEmpty()) {
            throw new AddressNotFoundException();
        }
        Address existingAddress = foundAddress.get();
        existingAddress.setName(address.getName());
        existingAddress.setPhoneNumber(address.getPhoneNumber());
        existingAddress.setAddressLineA(address.getAddressLineA());
        existingAddress.setAddressLineB(address.getAddressLineB());
        existingAddress.setPincode(address.getPincode());
        existingAddress.setActive(address.isActive());
        return existingAddress;
    }
}
