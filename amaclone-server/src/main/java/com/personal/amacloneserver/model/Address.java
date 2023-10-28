package com.personal.amacloneserver.model;

import com.personal.amacloneserver.dto.AddressDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private String name;
    private String phoneNumber;
    private String addressLineA;
    private String addressLineB;
    private String pincode;
    private boolean active = false;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Address(Customer customer, AddressDto addressDto) {
        this.customer = customer;
        this.name = addressDto.getName();
        this.phoneNumber = addressDto.getPhoneNumber();
        this.addressLineA = addressDto.getAddressLineA();
        this.addressLineB = addressDto.getAddressLineB();
        this.pincode = addressDto.getPincode();
    }
}
