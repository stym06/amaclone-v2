package com.personal.amacloneserver.dto;

import com.personal.amacloneserver.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String addressLineA;
    private String addressLineB;
    private String pincode;
    private boolean active;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.name = address.getName();
        this.phoneNumber = address.getPhoneNumber();
        this.addressLineA = address.getAddressLineA();
        this.addressLineB = address.getAddressLineB();
        this.pincode = address.getPincode();
        this.active = address.isActive();
    }
}
