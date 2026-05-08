package com.ore.preppc.dto;

import lombok.*;

@Getter
@Setter
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}