package com.personal.amacloneserver.controller;

import com.personal.amacloneserver.dto.CustomerAddressDto;
import com.personal.amacloneserver.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public ResponseEntity<CustomerAddressDto> getAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(this.accountService.getAccount());
    }
}
