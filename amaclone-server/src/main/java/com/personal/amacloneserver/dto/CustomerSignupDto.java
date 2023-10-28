package com.personal.amacloneserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerSignupDto {
    private String name;
    private String email;
    private String password;
}
