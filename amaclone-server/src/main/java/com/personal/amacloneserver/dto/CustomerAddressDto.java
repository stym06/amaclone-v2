package com.personal.amacloneserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerAddressDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private List<AddressDto> addresses;
}
